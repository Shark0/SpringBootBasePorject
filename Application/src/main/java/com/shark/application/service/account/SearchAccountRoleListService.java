package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SearchAccountRoleListService extends BaseQueryDataService<List<RoleDaoEntity>, List<RoleDaoEntity>> {

    public static final String INPUT_ACCOUNT_ID = "accountId";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ACCOUNT_ID);
    }

    @Override
    protected List<RoleDaoEntity> dataAccess(HashMap<String, String> parameters) {
        String accountId = parameters.get(INPUT_ACCOUNT_ID);
        return roleRepository.findByAccountId(accountId);
    }

    @Override
    protected ResponseDataEntity<List<RoleDaoEntity>> generateResultData(List<RoleDaoEntity> data) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
