package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.service.BaseResponseService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EditAccountService extends BaseResponseService {

    public static final String INPUT_ID = "id";
    public static final String INPUT_NAME = "name";
    public static final String INPUT_PASSWORD = "password";

    @Autowired
    private AccountRepository memberRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) throws Exception {
        String id = parameters.get(INPUT_ID);
        String name = parameters.get(INPUT_NAME);
        String password = parameters.get(INPUT_PASSWORD);
        AccountDaoEntity accountDaoEntity = memberRepository.findOne(Long.valueOf(id));
        if(accountDaoEntity == null) {
            throw new ResponseException(-1, "帳號不存在");
        }
        if(!StringUtil.isEmpty(name)) {
            accountDaoEntity.setName(name);
        }
        if(!StringUtil.isEmpty(password)) {
            accountDaoEntity.setPassword(password);
        }
        memberRepository.save(accountDaoEntity);
        return null;
    }
}
