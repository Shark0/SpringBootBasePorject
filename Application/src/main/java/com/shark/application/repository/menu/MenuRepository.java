package com.shark.application.repository.menu;


import com.shark.application.repository.menu.dao.MenuDaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuDaoEntity, Long> {

    @Query(value = "select * " +
            "from MENU " +
            "where MENU.ID not in (select MENU_ROLE.MENU_ID from MENU_ROLE) or " +
            "MENU.ID in (" +
            "select MENU_ROLE.MENU_ID from MENU_ROLE where MENU_ROLE.ROLE_ID in (" +
            "select ACCOUNT_ROLE.ROLE_ID from ACCOUNT_ROLE where ACCOUNT_ROLE.ACCOUNT_ID = ?1" +
            "))"
            , nativeQuery = true)
    List<MenuDaoEntity> findByAccountId(String accountId);

    @Query(value = "select * " +
            "from MENU " +
            "where MENU.ID = ?1 or " +
            "MENU.PARENT_ID = ?1"
            , nativeQuery = true)
    List<MenuDaoEntity> findByMenuIdOrParentId(Long id);

    @Query(value = "select * " +
            "from MENU " +
            "where MENU.PARENT_ID = 0 " +
            "order by MENU.SORT"
            , nativeQuery = true)
    List<MenuDaoEntity> findMainMenu();
}
