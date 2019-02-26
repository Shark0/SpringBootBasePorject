package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.repository.mysql.account.AccountRepository;
import com.shark.application.repository.mysql.account.dao.AccountDaoEntity;
import com.shark.application.service.BaseStringFromResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UpdateAccountService extends BaseStringFromResponseService {

    public static final String INPUT_NAME = "name";

    @Autowired
    private AccountRepository memberRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_NAME);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) throws Exception {
        AccountDaoEntity memberDaoEntity = memberRepository.findOne(Long.valueOf(accountId));
        memberDaoEntity.setName(parameters.get(INPUT_NAME));
        memberRepository.save(memberDaoEntity);
        return null;
    }
}
