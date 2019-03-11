package com.shark.application.service.role;

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
public class SearchRolePermissionListService extends BaseQueryDataService<List<PermissionDaoEntity>, List<PermissionDaoEntity>> {

    public static final String INPUT_ROLE_ID = "roleId";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ROLE_ID);
    }

    @Override
    protected List<PermissionDaoEntity> dataAccess(HashMap<String, String> parameters) {
        String roleId = parameters.get(INPUT_ROLE_ID);
        return permissionRepository.findByRoleId(roleId);
    }

    @Override
    protected ResponseDataEntity<List<PermissionDaoEntity>> generateResultData(List<PermissionDaoEntity> data) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
