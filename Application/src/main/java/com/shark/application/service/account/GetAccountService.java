package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.account.AccountRoleDtoEntity;
import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetAccountService extends BaseQueryDataService<AccountDaoEntity, AccountDaoEntity> {

    public static final String INPUT_ID = "id";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected AccountDaoEntity dataAccess(HashMap<String, String> parameters) {
        String accountId = parameters.get(INPUT_ID);
        return accountRepository.findOne(Long.valueOf(accountId));
    }

    @Override
    protected ResponseDataEntity<AccountDaoEntity> generateResultData(AccountDaoEntity data) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
