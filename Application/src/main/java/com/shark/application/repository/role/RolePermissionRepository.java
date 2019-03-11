package com.shark.application.repository.role;

import com.shark.application.repository.role.dao.RolePermissionDaoEntity;
import com.shark.application.repository.role.dao.id.RolePermissionIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermissionDaoEntity, RolePermissionIdEntity> {

}
