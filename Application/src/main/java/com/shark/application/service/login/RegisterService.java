package com.shark.application.service.login;

import com.google.common.collect.Lists;
import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.login.LoginDtoEntity;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RegisterService extends BaseQueryDataService<AccountDaoEntity, LoginDtoEntity> {

    public static final String INPUT_ACCOUNT = "account";
    public static final String INPUT_PASSWORD = "password";
    public static final String INPUT_NAME = "name";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_NAME, INPUT_ACCOUNT, INPUT_PASSWORD);
    }

    @Override
    protected AccountDaoEntity dataAccess(HashMap<String, String> parameters) throws Exception {
        AccountDaoEntity accountDaoEntity = accountRepository.findByAccount(INPUT_ACCOUNT);
        if(accountDaoEntity != null) {
            ResponseException exception = new ResponseException();
            exception.setReturnCode(-1);
            exception.setReturnMessage("Account exist");
            throw exception;
        }
        accountDaoEntity = new AccountDaoEntity();
        accountDaoEntity.setAccount(parameters.get(INPUT_ACCOUNT));
        accountDaoEntity.setPassword(parameters.get(INPUT_PASSWORD));
        accountDaoEntity.setName(parameters.get(INPUT_NAME));
        accountDaoEntity = accountRepository.save(accountDaoEntity);
        this.accountId = String.valueOf(accountDaoEntity.getId());
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
