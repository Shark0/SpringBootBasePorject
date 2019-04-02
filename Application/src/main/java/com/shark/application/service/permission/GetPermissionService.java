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
public class GetPermissionService extends BaseQueryDataService<PermissionDaoEntity, PermissionDaoEntity> {

    public static final String INPUT_ID = "id";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected PermissionDaoEntity dataAccess(String accountId, HashMap<String, String> parameters) {
        return permissionRepository.findById(Long.valueOf(parameters.get(INPUT_ID))).get();
    }

    @Override
    protected ResponseDataEntity<PermissionDaoEntity> generateResultData(String accountId, PermissionDaoEntity permissionDaoEntity) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(permissionDaoEntity);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
