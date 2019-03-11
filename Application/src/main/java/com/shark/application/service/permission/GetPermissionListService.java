package com.shark.application.service.permission;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GetPermissionListService extends BaseQueryDataService<List<PermissionDaoEntity>, List<PermissionDaoEntity>> {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return null;
    }

    @Override
    protected List<PermissionDaoEntity> dataAccess(HashMap<String, String> parameters) {
        return permissionRepository.findAll();
    }

    @Override
    protected ResponseDataEntity<List<PermissionDaoEntity>> generateResultData(List<PermissionDaoEntity> roleDaoEntityList) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(roleDaoEntityList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
