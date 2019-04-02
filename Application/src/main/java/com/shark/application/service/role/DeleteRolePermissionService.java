package com.shark.application.service.role;

import com.google.common.collect.Lists;
import com.shark.application.repository.role.RolePermissionRepository;
import com.shark.application.repository.role.dao.id.RolePermissionIdEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class DeleteRolePermissionService extends BaseResponseService {

    public static final String INPUT_ROLE_ID = "roleId";
    public static final String INPUT_PERMISSION_ID = "permissionId";


    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ROLE_ID, INPUT_PERMISSION_ID);
    }

    @Override
    protected Void dataAccess(String accountId, HashMap<String, String> parameters) {
        long roleId = Long.valueOf(parameters.get(INPUT_ROLE_ID));
        long permissionId = Long.valueOf(parameters.get(INPUT_PERMISSION_ID));
        RolePermissionIdEntity rolePermissionIdEntity = new RolePermissionIdEntity();
        rolePermissionIdEntity.setRoleId(roleId);
        rolePermissionIdEntity.setPermissionId(permissionId);
        rolePermissionRepository.deleteById(rolePermissionIdEntity);
        return null;
    }
}
