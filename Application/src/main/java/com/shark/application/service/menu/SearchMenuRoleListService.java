package com.shark.application.service.menu;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SearchMenuRoleListService extends BaseQueryDataService<List<RoleDaoEntity>, List<RoleDaoEntity>> {

    public static final String INPUT_MENU_ID = "menuId";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_MENU_ID);
    }

    @Override
    protected List<RoleDaoEntity> dataAccess(HashMap<String, String> parameters) {
        String menuId = parameters.get(INPUT_MENU_ID);
        return roleRepository.findByMenuId(menuId);
    }

    @Override
    protected ResponseDataEntity<List<RoleDaoEntity>> generateResultData(List<RoleDaoEntity> groupDaoEntityList) {
        ResponseDataEntity<List<RoleDaoEntity>> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(groupDaoEntityList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
