package com.shark.application.service.login;

import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.controller.auth.pojo.LoginDo;
import com.shark.application.controller.auth.pojo.LoginDto;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseQueryDataService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class LoginService extends BaseQueryDataService<LoginDo, LoginDto, LoginDto> {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected LoginDto process(AuthAccountDo authAccountDo, LoginDo loginDo) throws Exception {
        AccountDo accountDo = findAccount(loginDo);
        String accessToken = generateAccessToken(accountDo);
        String refreshToken = generateRefreshToken(accountDo);
        saveAccount(accountDo, accessToken, refreshToken);
        LoginDto loginDto = LoginDto.builder().accessToken(accessToken).refreshToken(refreshToken).accountName(accountDo.getName()).build();
        return loginDto;
    }

    private AccountDo findAccount(LoginDo loginDo) {
        AccountDo accountDo = accountRepository.findByAccount(loginDo.getAccount());
        if (accountDo == null) {
            WarningException exception = new WarningException("account.does.not.exist");
            throw exception;
        }
        String password = loginDo.getPassword();
        if (!bCryptPasswordEncoder.matches(password, accountDo.getPassword())) {
            throw new WarningException("password.error");
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
