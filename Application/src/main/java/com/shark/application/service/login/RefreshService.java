package com.shark.application.service.login;

import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.login.LoginDtoEntity;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RefreshService extends BaseQueryDataService<LoginDtoEntity, LoginDtoEntity> {

    public static final String INPUT_ACCESS_TOKEN = "accessToken";
    public static final String INPUT_REFRESH_TOKEN = "refreshToken";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(INPUT_ACCESS_TOKEN);
        list.add(INPUT_REFRESH_TOKEN);
        return list;
    }

    @Override
    protected LoginDtoEntity dataAccess(String accountId, HashMap<String, String> parameters) throws Exception {
        String accessToken = parameters.get(INPUT_ACCESS_TOKEN);
        String refreshToken = parameters.get(INPUT_REFRESH_TOKEN);
        String account = null;
        try {
            account = Jwts.parser()
                    .setSigningKey(SecurityConfiguration.REFRESH_SECRET.getBytes())
                    .parseClaimsJws(refreshToken.replace(SecurityConfiguration.REFRESH_PREFIX, ""))
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new ResponseException(-1, "Refresh Token Parser錯誤");
        }
        System.out.println("account: " + account);
        AccountDaoEntity accountDaoEntity =  accountRepository.findById(Long.valueOf(account)).get();
        String accountAccessToken = accountDaoEntity.getAccessToken();
        if(StringUtil.isEmpty(accountAccessToken) || !accountAccessToken.equalsIgnoreCase(accessToken)) {
            throw new ResponseException(-2, "Access Token 不一致");
        }

        LoginDtoEntity memberDtoEntity = new LoginDtoEntity();
        memberDtoEntity.setAccountName(accountDaoEntity.getName());
        accessToken = SecurityConfiguration.ACCESS_PREFIX + Jwts.builder()
                .setSubject(String.valueOf(accountDaoEntity.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConfiguration.ACCESS_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConfiguration.ACCESS_SECRET.getBytes())
                .compact();
        memberDtoEntity.setAccessToken(accessToken);

        refreshToken = SecurityConfiguration.REFRESH_PREFIX + Jwts.builder()
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
        responseDataEntity.setReturnCode(1);
        responseDataEntity.setData(loginDtoEntity);
        return responseDataEntity;
    }
}
