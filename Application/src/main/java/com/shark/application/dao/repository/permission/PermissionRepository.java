package com.shark.application.dao.repository.permission;

import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionDo, Long> {
    @Query(value = "select * " +
            "from PERMISSION " +
            "where PERMISSION.ID in (" +
            "select ROLE_PERMISSION.PERMISSION_ID from ROLE_PERMISSION where ROLE_PERMISSION.ROLE_ID = ?1" +
            ")", nativeQuery = true)
    List<PermissionDo> findByRoleId(Long roleId);

    @Query(value = "select * " +
            "from PERMISSION " +
            "where PERMISSION.ID in (" +
            "select ROLE_PERMISSION.PERMISSION_ID from ROLE_PERMISSION where ROLE_PERMISSION.ROLE_ID in (" +
            "select ACCOUNT_ROLE.ROLE_ID from ACCOUNT_ROLE where ACCOUNT_ROLE.ACCOUNT_ID = ?1" +
            "))", nativeQuery = true)
    List<PermissionDo> findByAccount(String account);
}
