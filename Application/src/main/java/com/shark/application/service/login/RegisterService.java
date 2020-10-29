package com.shark.application.service.login;

import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.controller.auth.pojo.LoginDto;
import com.shark.application.controller.auth.pojo.RegisterDo;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class RegisterService extends BaseQueryDataService<RegisterDo, LoginDto, LoginDto> {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected LoginDto process(AuthAccountDo authAccountDo, RegisterDo registerDo) throws Exception {
        checkEmail(registerDo.getAccount());
        AccountDo accountDo = createAccount(registerDo);
        String accessToken = generateAccessToken(accountDo);
        String refreshToken = generateRefreshToken(accountDo);
        saveAccount(accountDo, accessToken, refreshToken);
        LoginDto loginDto = LoginDto.builder().accessToken(accessToken).refreshToken(refreshToken).accountName(accountDo.getName()).build();
        return loginDto;
    }

    private void checkEmail(String account) {
        if (!StringUtil.isEmail(account)) {
            throw new WarningException("email.format.error");
        }
    }

    private AccountDo createAccount(RegisterDo registerDo) {
        AccountDo accountDo = new AccountDo();
        accountDo.setAccount(registerDo.getAccount());
        accountDo.setName(registerDo.getName());
        String encryptedPwd = bCryptPasswordEncoder.encode(registerDo.getPassword());
        accountDo.setPassword(encryptedPwd);
        return accountRepository.save(accountDo);
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
