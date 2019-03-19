package com.shark.application.service.permission;

import com.google.common.collect.Lists;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.service.BaseResponseService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EditPermissionService extends BaseResponseService {

    public static final String INPUT_ID = "id";
    public static final String INPUT_NAME = "name";
    public static final String INPUT_CODE = "code";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) {
        long id = Long.valueOf(parameters.get(INPUT_ID));
        PermissionDaoEntity permissionDaoEntity = permissionRepository.findOne(id);
        if(permissionDaoEntity == null) {
            throw new ResponseException(-1, "Permission doesn't exist");
        }
        String name = parameters.get(INPUT_NAME);
        if(!StringUtil.isEmpty(name)) {
            permissionDaoEntity.setName(name);
        }
        String code = parameters.get(INPUT_CODE);
        if(!StringUtil.isEmpty(code)) {
            permissionDaoEntity.setCode(code);
        }
        permissionRepository.save(permissionDaoEntity);
        return null;
    }
}
