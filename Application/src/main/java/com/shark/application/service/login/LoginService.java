package com.shark.application.service.login;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.login.LoginDtoEntity;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.service.BaseQueryDataService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LoginService extends BaseQueryDataService<LoginDtoEntity, LoginDtoEntity> {


    public static final String INPUT_ACCOUNT = "account";
    public static final String INPUT_PASSWORD = "password";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ACCOUNT, INPUT_PASSWORD);
    }

    @Override
    protected LoginDtoEntity dataAccess(String accountId, HashMap<String, String> parameters) throws Exception {
        String account = parameters.get(INPUT_ACCOUNT);
        String password = parameters.get(INPUT_PASSWORD);
        AccountDaoEntity accountDaoEntity = accountRepository.findByAccount(account);
        if(accountDaoEntity == null){
            ResponseException exception = new ResponseException();
            exception.setReturnCode(-1);
            exception.setReturnMessage("Account doesn't exist");
            throw exception;
        }
        if(!password.equalsIgnoreCase(accountDaoEntity.getPassword())) {
            ResponseException exception = new ResponseException();
            exception.setReturnCode(-2);
            exception.setReturnMessage("Password error");
            throw exception;
        }

        LoginDtoEntity memberDtoEntity = new LoginDtoEntity();
        memberDtoEntity.setAccountName(accountDaoEntity.getName());
        String accessToken = SecurityConfiguration.ACCESS_PREFIX + Jwts.builder()
                .setSubject(String.valueOf(accountDaoEntity.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.ACCESS_SECRET.getBytes())
                .compact();
        memberDtoEntity.setAccessToken(accessToken);
        String refreshToken = SecurityConfiguration.REFRESH_PREFIX + Jwts.builder()
                .setSubject(String.valueOf(accountDaoEntity.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.REFRESH_SECRET.getBytes())
                .compact();
        memberDtoEntity.setRefreshToken(refreshToken);

        accountDaoEntity.setAccessToken(accessToken);
        accountDaoEntity.setRefreshToken(refreshToken);
        accountRepository.save(accountDaoEntity);
        return memberDtoEntity;
    }

    @Override
    protected ResponseDataEntity<LoginDtoEntity> generateResultData(String accountId, LoginDtoEntity loginDtoEntity) {
        ResponseDataEntity<LoginDtoEntity> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(loginDtoEntity);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
