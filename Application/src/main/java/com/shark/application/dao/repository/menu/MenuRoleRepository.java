package com.shark.application.dao.repository.menu;

import com.shark.application.dao.repository.menu.pojo.MenuRoleDo;
import com.shark.application.dao.repository.menu.pojo.id.MenuRoleIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRoleRepository extends JpaRepository<MenuRoleDo, MenuRoleIdEntity>{

    void deleteByMenuId(Long menuId);
}
