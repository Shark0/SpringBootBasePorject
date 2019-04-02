package com.shark.application.service.role;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GetRoleListService extends BaseQueryDataService<List<RoleDaoEntity>, List<RoleDaoEntity>> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return null;
    }

    @Override
    protected List<RoleDaoEntity> dataAccess(String accountId, HashMap<String, String> parameters) {
        return roleRepository.findAll();
    }

    @Override
    protected ResponseDataEntity<List<RoleDaoEntity>> generateResultData(String accountId, List<RoleDaoEntity> groupDaoEntityList) {
        ResponseDataEntity responseDataEntity = new ResponseDataEntity();
        responseDataEntity.setData(groupDaoEntityList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
