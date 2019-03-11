package com.shark.application.service.role;

import com.google.common.collect.Lists;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.role.RolePermissionRepository;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.repository.role.dao.RolePermissionDaoEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AddRolePermissionService extends BaseResponseService {

    public static final String INPUT_ROLE_ID = "roleId";
    public static final String INPUT_PERMISSION_ID = "permissionId";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ROLE_ID, INPUT_PERMISSION_ID);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) {
        long roleId = Long.valueOf(parameters.get(INPUT_ROLE_ID));
        long permissionId = Long.valueOf(parameters.get(INPUT_PERMISSION_ID));
        RoleDaoEntity roleDaoEntity = roleRepository.findOne(roleId);
        if(roleDaoEntity == null) {
            throw new ResponseException(-1, "Role doesn't exist");
        }

        PermissionDaoEntity permissionDaoEntity = permissionRepository.findOne(permissionId);
        if(permissionDaoEntity == null) {
            throw new ResponseException(-1, "Permission doesn't exist");
        }
        RolePermissionDaoEntity rolePermissionDaoEntity = new RolePermissionDaoEntity();
        rolePermissionDaoEntity.setRoleId(roleId);
        rolePermissionDaoEntity.setPermissionId(permissionId);
        rolePermissionRepository.save(rolePermissionDaoEntity);
        return null;
    }
}
