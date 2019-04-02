package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.account.AccountRoleDtoEntity;
import com.shark.application.dto.menu.MenuRoleDtoEntity;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetAccountRoleListService extends BaseQueryDataService<List<AccountRoleDtoEntity>, List<AccountRoleDtoEntity>> {

    public static final String INPUT_ID = "id";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected List<AccountRoleDtoEntity> dataAccess(String accountId, HashMap<String, String> parameters) {
        String accountIdParameter = parameters.get(INPUT_ID);
        List<RoleDaoEntity> accountRoleList = roleRepository.findByAccountId(accountIdParameter);
        HashMap<Long, RoleDaoEntity> accountRoleHashMap = new HashMap<>();
        for(RoleDaoEntity roleDaoEntity: accountRoleList) {
            accountRoleHashMap.put(roleDaoEntity.getId(), roleDaoEntity);
        }
        List<RoleDaoEntity> roleDaoEntityList = roleRepository.findAll();
        List<AccountRoleDtoEntity> accountRoleDtoEntityList = new ArrayList<>();
        for(RoleDaoEntity roleDaoEntity: roleDaoEntityList) {
            AccountRoleDtoEntity accountRoleDtoEntity = new AccountRoleDtoEntity();
            accountRoleDtoEntity.setRoleId(roleDaoEntity.getId());
            accountRoleDtoEntity.setRoleName(roleDaoEntity.getName());
            accountRoleDtoEntity.setAdd(accountRoleHashMap.get(roleDaoEntity.getId()) != null);
            accountRoleDtoEntityList.add(accountRoleDtoEntity);
        }
        return accountRoleDtoEntityList;
    }

    @Override
    protected ResponseDataEntity<List<AccountRoleDtoEntity>> generateResultData(String accountId, List<AccountRoleDtoEntity> data) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
