package com.shark.application.service.permission;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AddPermissionService extends BaseQueryDataService<PermissionDaoEntity, PermissionDaoEntity> {

    public static final String INPUT_NAME = "name";

    public static final String INPUT_CODE = "code";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_NAME, INPUT_CODE);
    }

    @Override
    protected PermissionDaoEntity dataAccess(String accountId, HashMap<String, String> parameters) {
        PermissionDaoEntity permissionDaoEntity = new PermissionDaoEntity();
        permissionDaoEntity.setName(parameters.get(INPUT_NAME));
        permissionDaoEntity.setCode(parameters.get(INPUT_CODE));
        permissionDaoEntity = permissionRepository.save(permissionDaoEntity);
        return permissionDaoEntity;
    }

    @Override
    protected ResponseDataEntity<PermissionDaoEntity> generateResultData(String accountId, PermissionDaoEntity permissionDaoEntity) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(permissionDaoEntity);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
