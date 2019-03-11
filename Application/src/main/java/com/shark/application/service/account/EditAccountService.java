package com.shark.application.service.account;

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

    public static final String INPUT_NAME = "name";
    public static final String INPUT_PASSWORD = "password";

    @Autowired
    private AccountRepository memberRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return null;
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) throws Exception {
        String name = parameters.get(INPUT_NAME);
        String password = parameters.get(INPUT_PASSWORD);
        AccountDaoEntity memberDaoEntity = memberRepository.findOne(Long.valueOf(accountId));
        if(!StringUtil.isEmpty(name)) {
            memberDaoEntity.setName(name);
        }
        if(!StringUtil.isEmpty(password)) {
            memberDaoEntity.setName(password);
        }
        memberRepository.save(memberDaoEntity);
        return null;
    }
}
