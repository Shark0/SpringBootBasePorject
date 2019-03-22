package com.shark.application.service.role;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.repository.role.RolePermissionRepository;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.repository.role.dao.RolePermissionDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AddRoleService extends BaseQueryDataService<RoleDaoEntity, RoleDaoEntity> {

    public static final String INPUT_NAME = "name";
    public static final String INPUT_PERMISSION_ID_LIST_JSON = "permissionIdListJson";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_NAME);
    }

    @Override
    protected RoleDaoEntity dataAccess(HashMap<String, String> parameters) {
        RoleDaoEntity roleDaoEntity = new RoleDaoEntity();
        roleDaoEntity.setName(parameters.get(INPUT_NAME));
        roleDaoEntity = roleRepository.save(roleDaoEntity);

        String permissionIdListJson = parameters.get(INPUT_PERMISSION_ID_LIST_JSON);
        Long roleId = roleDaoEntity.getId();
        if(!StringUtil.isEmpty(permissionIdListJson)) {
            Gson gson = new Gson();
            List<Long> permissionIdList = gson.fromJson(permissionIdListJson, new TypeToken<List<Long>>(){}.getType());
            for(Long permissionId: permissionIdList) {
                PermissionDaoEntity permissionDaoEntity = permissionRepository.findOne(permissionId);
                if(permissionDaoEntity != null) {
                    RolePermissionDaoEntity rolePermissionDaoEntity = new RolePermissionDaoEntity();
                    rolePermissionDaoEntity.setRoleId(roleId);
                    rolePermissionDaoEntity.setPermissionId(permissionId);
                    rolePermissionRepository.save(rolePermissionDaoEntity);
                }
            }
        }
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
