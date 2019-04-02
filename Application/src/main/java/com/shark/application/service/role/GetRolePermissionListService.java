package com.shark.application.service.role;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.role.RolePermissionDtoEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetRolePermissionListService extends BaseQueryDataService<List<RolePermissionDtoEntity>, List<RolePermissionDtoEntity>> {

    public static final String INPUT_ROLE_ID = "roleId";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ROLE_ID);
    }

    @Override
    protected List<RolePermissionDtoEntity> dataAccess(String accountId, HashMap<String, String> parameters) {
        String roleId = parameters.get(INPUT_ROLE_ID);
        List<PermissionDaoEntity> rolePermissionList = permissionRepository.findByRoleId(roleId);
        HashMap<Long, PermissionDaoEntity> rolePermissionHashMap = new HashMap<>();
        for(PermissionDaoEntity permissionDaoEntity: rolePermissionList) {
            rolePermissionHashMap.put(permissionDaoEntity.getId(), permissionDaoEntity);
        }
        List<PermissionDaoEntity> permissionList = permissionRepository.findAll();
        List<RolePermissionDtoEntity> rolePermissionDtoEntityList = new ArrayList<>();
        for(PermissionDaoEntity permissionDaoEntity: permissionList) {
            RolePermissionDtoEntity rolePermissionDtoEntity = new RolePermissionDtoEntity();
            rolePermissionDtoEntity.setPermissionId(permissionDaoEntity.getId());
            rolePermissionDtoEntity.setPermissionName(permissionDaoEntity.getName());
            rolePermissionDtoEntity.setAdd((rolePermissionHashMap.get(permissionDaoEntity.getId()) != null));
            rolePermissionDtoEntityList.add(rolePermissionDtoEntity);
        }
        return rolePermissionDtoEntityList;
    }

    @Override
    protected ResponseDataEntity<List<RolePermissionDtoEntity>> generateResultData(String accountId, List<RolePermissionDtoEntity> data) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
