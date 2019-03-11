package com.shark.application.repository.permission;

import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionDaoEntity, Long> {
    @Query(value = "select * " +
            "from PERMISSION " +
            "where PERMISSION.ID in (" +
            "select ROLE_PERMISSION.PERMISSION_ID from ROLE_PERMISSION where ROLE_PERMISSION.ROLE_ID = ?1" +
            ")", nativeQuery = true)
    List<PermissionDaoEntity> findByRoleId(String roleId);

    @Query(value = "select * " +
            "from PERMISSION " +
            "where PERMISSION.ID in (" +
            "select ROLE_PERMISSION.PERMISSION_ID from ROLE_PERMISSION where ROLE_PERMISSION.ROLE_ID in (" +
            "select ACCOUNT_ROLE.ROLE_ID from ACCOUNT_ROLE where ACCOUNT_ROLE.ACCOUNT_ID = ?1" +
            "))", nativeQuery = true)
    List<PermissionDaoEntity> findByAccount(String account);
}
