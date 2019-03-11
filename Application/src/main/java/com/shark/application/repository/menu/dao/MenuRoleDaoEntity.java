package com.shark.application.repository.menu.dao;

import com.shark.application.repository.menu.dao.id.MenuRoleIdEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu_role")
@IdClass(MenuRoleIdEntity.class)
public class MenuRoleDaoEntity implements Serializable {

    @Id
    private Long menuId;

    @Id
    private Long roleId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
