package com.shark.application.dao.repository.role;

import com.shark.application.dao.repository.role.pojo.RoleDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleDo, Long> {

    @Query(value = "select * " +
            "from ROLE " +
            "where ROLE.ID in (select MENU_ROLE.ROLE_ID from MENU_ROLE where MENU_ROLE.MENU_ID = ?1)", nativeQuery = true)
    List<RoleDo> findByMenuId(Long menuId);

    @Query(value = "select * " +
            "from ROLE " +
            "where ROLE.ID in (select ACCOUNT_ROLE.ROLE_ID from ACCOUNT_ROLE where ACCOUNT_ROLE.ACCOUNT_ID = ?1)", nativeQuery = true)
    List<RoleDo> findByAccountId(Long accountId);
}
