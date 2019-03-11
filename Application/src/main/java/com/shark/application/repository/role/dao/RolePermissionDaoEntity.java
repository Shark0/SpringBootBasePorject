package com.shark.application.repository.role.dao;

import com.shark.application.repository.role.dao.id.RolePermissionIdEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "role_permission")
@IdClass(RolePermissionIdEntity.class)
public class RolePermissionDaoEntity {

    @Id
    private Long roleId;

    @Id
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}