package com.shark.application.service.menu;

import com.google.common.collect.Lists;
import com.shark.application.repository.menu.MenuRoleRepository;
import com.shark.application.repository.menu.dao.MenuRoleDaoEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AddMenuRoleService extends BaseResponseService {

    public static final String INPUT_MENU_ID = "menuId";

    public static final String INPUT_ROLE_ID = "roleId";

    @Autowired
    private MenuRoleRepository menuRoleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_MENU_ID, INPUT_ROLE_ID);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) {
        String menuId = parameters.get(INPUT_MENU_ID);
        String roleId = parameters.get(INPUT_ROLE_ID);
        MenuRoleDaoEntity menuRoleDaoEntity = new MenuRoleDaoEntity();
        menuRoleDaoEntity.setMenuId(Long.valueOf(menuId));
        menuRoleDaoEntity.setRoleId(Long.valueOf(roleId));
        menuRoleRepository.save(menuRoleDaoEntity);
        return null;
    }
}
