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
public class AddRoleService extends BaseQueryDataService<RoleDaoEntity, RoleDaoEntity> {

    public static final String INPUT_NAME = "name";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_NAME);
    }

    @Override
    protected RoleDaoEntity dataAccess(HashMap<String, String> parameters) {
        RoleDaoEntity roleDaoEntity = new RoleDaoEntity();
        roleDaoEntity.setName(parameters.get(INPUT_NAME));
        roleDaoEntity = roleRepository.save(roleDaoEntity);
        return roleDaoEntity;
    }

    @Override
    protected ResponseDataEntity<RoleDaoEntity> generateResultData(RoleDaoEntity roleDaoEntity) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(roleDaoEntity);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
