package com.shark.application.service.role;

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
public class GetRoleService extends BaseQueryDataService<RoleDaoEntity, RoleDaoEntity> {

    public static final String INPUT_ID = "id";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected RoleDaoEntity dataAccess(String accountId, HashMap<String, String> parameters) {
        return roleRepository.findById(Long.valueOf(parameters.get(INPUT_ID))).get();
    }

    @Override
    protected ResponseDataEntity<RoleDaoEntity> generateResultData(String accountId, RoleDaoEntity groupDaoEntityList) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(groupDaoEntityList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
