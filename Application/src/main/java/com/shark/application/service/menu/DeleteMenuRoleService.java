package com.shark.application.service.menu;

import com.shark.application.controller.menu.pojo.MenuRoleDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.menu.MenuRoleRepository;
import com.shark.application.dao.repository.menu.pojo.id.MenuRoleIdEntity;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteMenuRoleService extends BaseResponseService<MenuRoleDio> {

    private final MenuRoleRepository menuRoleRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, MenuRoleDio menuRoleDio) throws Exception {
        MenuRoleIdEntity menuPermissionIdEntity = new MenuRoleIdEntity();
        menuPermissionIdEntity.setMenuId(menuRoleDio.getMenuId());
        menuPermissionIdEntity.setRoleId(menuRoleDio.getRoleId());
        menuRoleRepository.deleteById(menuPermissionIdEntity);
        return null;
    }
}
