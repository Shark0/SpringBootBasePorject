package com.shark.application.repository.menu;

import com.shark.application.repository.menu.dao.MenuRoleDaoEntity;
import com.shark.application.repository.menu.dao.id.MenuRoleIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRoleRepository extends JpaRepository<MenuRoleDaoEntity, MenuRoleIdEntity>{

}
