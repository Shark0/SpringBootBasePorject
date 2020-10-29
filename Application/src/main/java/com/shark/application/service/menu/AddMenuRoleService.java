package com.shark.application.service.menu;

import com.shark.application.controller.menu.pojo.MenuRoleDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.menu.MenuRoleRepository;
import com.shark.application.dao.repository.menu.pojo.MenuRoleDo;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AddMenuRoleService extends BaseResponseService<MenuRoleDio> {


    @Autowired
    private MenuRoleRepository menuRoleRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, MenuRoleDio menuRoleDio) throws Exception {
        MenuRoleDo menuRoleDo = new MenuRoleDo();
        menuRoleDo.setMenuId(menuRoleDio.getMenuId());
        menuRoleDo.setRoleId(menuRoleDio.getRoleId());
        menuRoleRepository.save(menuRoleDo);
        return null;
    }
}
