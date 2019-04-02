package com.shark.application.service.permission;

import com.shark.application.exception.ResponseException;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.role.RolePermissionRepository;
import com.shark.application.repository.role.dao.RolePermissionDaoEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class DeletePermissionService extends BaseResponseService {

    public static final String INPUT_ID = "id";

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(INPUT_ID);
        return list;
    }

    @Override
    protected Void dataAccess(String accountId, HashMap<String, String> parameters) {
        long id = Long.valueOf(parameters.get(INPUT_ID));
        RolePermissionDaoEntity rolePermissionDaoEntity = rolePermissionRepository.findByPermissionId(id);
        if(rolePermissionDaoEntity != null) {
            throw new ResponseException(-1, "權限跟角色榜定，請先刪除榜定");
        }
        permissionRepository.deleteById(id);
        return null;
    }
}
