package com.shark.application.dao.repository.role;

import com.shark.application.dao.repository.role.pojo.RolePermissionDo;
import com.shark.application.dao.repository.role.pojo.id.RolePermissionIdDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermissionDo, RolePermissionIdDo> {

    void deleteByPermissionId(long permissionId);

    void deleteByRoleId(Long roleId);
}
