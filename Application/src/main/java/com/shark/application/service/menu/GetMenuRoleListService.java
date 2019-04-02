package com.shark.application.service.menu;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.menu.MenuRoleDtoEntity;
import com.shark.application.dto.role.RolePermissionDtoEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetMenuRoleListService extends BaseQueryDataService<List<MenuRoleDtoEntity>, List<MenuRoleDtoEntity>> {

    public static final String INPUT_MENU_ID = "menuId";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_MENU_ID);
    }

    @Override
    protected List<MenuRoleDtoEntity> dataAccess(String accountId, HashMap<String, String> parameters) {
        String menuId = parameters.get(INPUT_MENU_ID);
        List<RoleDaoEntity> menuRoleList = roleRepository.findByMenuId(menuId);
        HashMap<Long, RoleDaoEntity> menuRoleHashMap = new HashMap<>();
        for(RoleDaoEntity roleDaoEntity: menuRoleList) {
            menuRoleHashMap.put(roleDaoEntity.getId(), roleDaoEntity);
        }
        List<RoleDaoEntity> roleDaoEntityList = roleRepository.findAll();
        List<MenuRoleDtoEntity> menuRoleDtoEntityList = new ArrayList<>();
        for(RoleDaoEntity roleDaoEntity: roleDaoEntityList) {
            MenuRoleDtoEntity menuRoleDtoEntity = new MenuRoleDtoEntity();
            menuRoleDtoEntity.setRoleId(roleDaoEntity.getId());
            menuRoleDtoEntity.setRoleName(roleDaoEntity.getName());
            menuRoleDtoEntity.setAdd(menuRoleHashMap.get(roleDaoEntity.getId()) != null);
            menuRoleDtoEntityList.add(menuRoleDtoEntity);
        }
        return menuRoleDtoEntityList;
    }

    @Override
    protected ResponseDataEntity<List<MenuRoleDtoEntity>> generateResultData(String accountId, List<MenuRoleDtoEntity> groupDaoEntityList) {
        ResponseDataEntity<List<MenuRoleDtoEntity>> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(groupDaoEntityList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
