package com.shark.application.service.account;
import com.shark.application.repository.account.AccountRoleRepository;
import com.shark.application.repository.account.dao.id.AccountRoleIdEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeleteAccountRoleService extends BaseResponseService {

    public static final String INPUT_ACCOUNT_ID = "accountId";
    public static final String INPUT_ROLE_ID = "roleId";

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(INPUT_ACCOUNT_ID);
        list.add(INPUT_ROLE_ID);
        return list;
    }

    @Override
    protected Void dataAccess(String accountId, HashMap<String, String> parameters) {
        AccountRoleIdEntity accountRoleIdEntity = new AccountRoleIdEntity();
        accountRoleIdEntity.setAccountId(Long.valueOf(parameters.get(INPUT_ACCOUNT_ID)));
        accountRoleIdEntity.setRoleId(Long.valueOf(parameters.get(INPUT_ROLE_ID)));
        accountRoleRepository.deleteById(accountRoleIdEntity);
        return null;
    }
}
