package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.repository.account.AccountRoleRepository;
import com.shark.application.repository.account.dao.AccountRoleDaoEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AddAccountRoleService extends BaseResponseService {

    public static final String INPUT_ACCOUNT_ID = "accountId";
    public static final String INPUT_ROLE_ID = "roleId";

    @Autowired
    private AccountRoleRepository accountGroupRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ACCOUNT_ID, INPUT_ROLE_ID);
    }

    @Override
    protected Void dataAccess(String accountId, HashMap<String, String> parameters) {
        AccountRoleDaoEntity entity = new AccountRoleDaoEntity();
        entity.setAccountId(Long.valueOf(parameters.get(INPUT_ACCOUNT_ID)));
        entity.setRoleId(Long.valueOf(parameters.get(INPUT_ROLE_ID)));
        accountGroupRepository.save(entity);
        return null;
    }
}
