package com.shark.application.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shark.application.repository.menu.dao.MenuDaoEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuRoleDtoEntity {

    private Long roleId;

    private String roleName;

    private boolean isAdd;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
