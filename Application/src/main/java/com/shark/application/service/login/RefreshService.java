package com.shark.application.service.login;

import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.controller.auth.pojo.LoginDto;
import com.shark.application.controller.auth.pojo.RefreshDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class RefreshService extends BaseQueryDataService<RefreshDo, LoginDto, LoginDto> {


    private final AccountRepository accountRepository;

    @Override
    protected LoginDto process(AuthAccountDo authAccountDo, RefreshDo refreshDo) throws Exception {
        AccountDo accountDo = findAccount(refreshDo);
        String accessToken = generateAccessToken(accountDo);
        String refreshToken = generateRefreshToken(accountDo);
        saveAccount(accountDo, accessToken, refreshToken);
        LoginDto loginDto = LoginDto.builder().accessToken(accessToken).refreshToken(refreshToken).accountName(accountDo.getName()).build();
        return loginDto;
    }

    private AccountDo findAccount(RefreshDo refreshDo) {
        String accountId = null;
        try {
            accountId = Jwts.parser()
                    .setSigningKey(SecurityConfiguration.REFRESH_SECRET.getBytes())
                    .parseClaimsJws(refreshDo.getRefreshToken().replace(SecurityConfiguration.REFRESH_PREFIX, ""))
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new WarningException("parse.refresh.token.error");
        }

        AccountDo accountDo =  accountRepository.findById(Long.valueOf(accountId)).get();
        String accessToken = accountDo.getAccessToken();
        if(StringUtil.isEmpty(accessToken) || !accessToken.equalsIgnoreCase(refreshDo.getAccessToken())) {
            throw new WarningException("access.token.error");
        }
        return accountDo;
    }

    private String generateAccessToken(AccountDo accountDo) {
        return Jwts.builder()
                .setSubject(String.valueOf(accountDo.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.ACCESS_SECRET.getBytes())
                .compact();
    }

    private String generateRefreshToken(AccountDo accountDo) {
        return Jwts.builder()
                .setSubject(String.valueOf(accountDo.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.REFRESH_SECRET.getBytes())
                .compact();
    }

    private void saveAccount(AccountDo accountDo, String accessToken, String refreshToken) {
        accountDo.setAccessToken(accessToken);
        accountDo.setRefreshToken(refreshToken);
        accountRepository.save(accountDo);
    }

    @Override
    protected ResponseDto<LoginDto> generateResult(AuthAccountDo authAccountDo, LoginDto loginDto) {
        ResponseDto<LoginDto> responseDataEntity = new ResponseDto<>();
        responseDataEntity.setResultData(loginDto);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
