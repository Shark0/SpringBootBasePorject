package com.shark.application.service.login;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.login.LoginDtoEntity;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.mysql.account.AccountRepository;
import com.shark.application.repository.mysql.account.dao.AccountDaoEntity;
import com.shark.application.config.security.SecurityConfiguration;
import com.shark.application.service.BaseStringFromResponseDataService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class LoginService extends BaseStringFromResponseDataService<AccountDaoEntity, LoginDtoEntity> {


    public static final String INPUT_ACCOUNT = "account";
    public static final String INPUT_PASSWORD = "password";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ACCOUNT, INPUT_PASSWORD);
    }

    @Override
    protected AccountDaoEntity dataAccess(HashMap<String, String> parameters) throws Exception {
        String account = parameters.get(INPUT_ACCOUNT);
        String password = parameters.get(INPUT_PASSWORD);

        AccountDaoEntity accountDaoEntity = accountRepository.findByAccount(account);
        this.accountId = String.valueOf(accountDaoEntity.getId());
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
        return accountDaoEntity;
    }

    @Override
    protected ResponseDataEntity<LoginDtoEntity> generateResultData(AccountDaoEntity accountDaoEntity) {
        ResponseDataEntity<LoginDtoEntity> responseDataEntity = new ResponseDataEntity<>();
        LoginDtoEntity memberDtoEntity = new LoginDtoEntity();
        memberDtoEntity.setAccountId(accountDaoEntity.getId());
        memberDtoEntity.setAccountName(accountDaoEntity.getAccount());
        String jwt = SecurityConfiguration.TOKEN_PREFIX + Jwts.builder()
                .setSubject(accountId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.SECRET.getBytes())
                .compact();
        memberDtoEntity.setJwt(jwt);
        responseDataEntity.setData(memberDtoEntity);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
