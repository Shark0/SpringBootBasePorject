package com.shark.application;


import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.AccountRoleRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.repository.account.dao.AccountRoleDaoEntity;
import com.shark.application.repository.menu.MenuRepository;
import com.shark.application.repository.menu.MenuRoleRepository;
import com.shark.application.repository.menu.dao.MenuDaoEntity;
import com.shark.application.repository.menu.dao.MenuRoleDaoEntity;
import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.repository.role.RolePermissionRepository;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.repository.role.dao.RolePermissionDaoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuRoleRepository menuRoleRepository;

	@Test
	public void testContext() throws Exception {
		//create 'root' account
        String account = "root";
        AccountDaoEntity accountDaoEntity = accountRepository.findByAccount(account);
        if(accountDaoEntity != null) {
            return;
        }
        accountDaoEntity = new AccountDaoEntity();
        accountDaoEntity.setName("root");
        accountDaoEntity.setAccount("root");
        accountDaoEntity.setPassword("root");
        accountDaoEntity = accountRepository.save(accountDaoEntity);
        long accountId = accountDaoEntity.getId();
        System.out.println("root account id: " + accountId);

        //create 'admin' role
        RoleDaoEntity adminRoleDaoEntity = new RoleDaoEntity();
        adminRoleDaoEntity.setName("admin");
        adminRoleDaoEntity = roleRepository.save(adminRoleDaoEntity);
        System.out.println("admin role id: " + adminRoleDaoEntity.getId());

        //add 'admin' role to 'root' account
        AccountRoleDaoEntity accountRoleDaoEntity = new AccountRoleDaoEntity();
        accountRoleDaoEntity.setAccountId(accountDaoEntity.getId());
        accountRoleDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        accountRoleRepository.save(accountRoleDaoEntity);

        //create 'permission' permission
        PermissionDaoEntity permissionPermissionDaoEntity = new PermissionDaoEntity();
        permissionPermissionDaoEntity.setName("權限");
        permissionPermissionDaoEntity.setCode("permission");
        permissionPermissionDaoEntity = permissionRepository.save(permissionPermissionDaoEntity);
        System.out.println("permission permission id: " + permissionPermissionDaoEntity.getId());

        //add 'permission' permission to 'admin' role
        RolePermissionDaoEntity permissionRolePermissionDaoEntity = new RolePermissionDaoEntity();
        permissionRolePermissionDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        permissionRolePermissionDaoEntity.setPermissionId(permissionPermissionDaoEntity.getId());
        rolePermissionRepository.save(permissionRolePermissionDaoEntity);

        //create 'role' permission
        PermissionDaoEntity rolePermissionDaoEntity = new PermissionDaoEntity();
        rolePermissionDaoEntity.setName("角色");
        rolePermissionDaoEntity.setCode("role");
        rolePermissionDaoEntity = permissionRepository.save(rolePermissionDaoEntity);
        System.out.println("role permission id: " + rolePermissionDaoEntity.getId());

        //add 'role' permission to 'admin' role
        RolePermissionDaoEntity roleRolePermissionDaoEntity = new RolePermissionDaoEntity();
        roleRolePermissionDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        roleRolePermissionDaoEntity.setPermissionId(rolePermissionDaoEntity.getId());
        rolePermissionRepository.save(roleRolePermissionDaoEntity);

        //create 'menu' permission
        PermissionDaoEntity menuPermissionDaoEntity = new PermissionDaoEntity();
        menuPermissionDaoEntity.setName("選單");
        menuPermissionDaoEntity.setCode("menu");
        menuPermissionDaoEntity = permissionRepository.save(menuPermissionDaoEntity);
        System.out.println("menu permission id: " + menuPermissionDaoEntity.getId());

        //add 'menu' permission to 'admin' role
        RolePermissionDaoEntity menuRolePermissionDaoEntity = new RolePermissionDaoEntity();
        menuRolePermissionDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        menuRolePermissionDaoEntity.setPermissionId(menuPermissionDaoEntity.getId());
        rolePermissionRepository.save(menuRolePermissionDaoEntity);

        //create 'account' permission
        PermissionDaoEntity accountPermissionDaoEntity = new PermissionDaoEntity();
        accountPermissionDaoEntity.setName("帳號");
        accountPermissionDaoEntity.setCode("account");
        accountPermissionDaoEntity = permissionRepository.save(accountPermissionDaoEntity);
        System.out.println("account permission id: " + accountPermissionDaoEntity.getId());

        //add 'account' permission to 'admin' role
        RolePermissionDaoEntity accountRolePermissionDaoEntity = new RolePermissionDaoEntity();
        accountRolePermissionDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        accountRolePermissionDaoEntity.setPermissionId(accountPermissionDaoEntity.getId());
        rolePermissionRepository.save(accountRolePermissionDaoEntity);

        //create home menu
        MenuDaoEntity homeMenuDaoEntity = new MenuDaoEntity();
        homeMenuDaoEntity.setParentId((long) 0);
        homeMenuDaoEntity.setName("首頁");
        homeMenuDaoEntity.setPath("/home/main");
        menuRepository.save(homeMenuDaoEntity);

        //create permission manage menu
        MenuDaoEntity accountPermissionManageMenuDaoEntity = new MenuDaoEntity();
        accountPermissionManageMenuDaoEntity.setParentId((long) 0);
        accountPermissionManageMenuDaoEntity.setName("帳號權限管理");
        accountPermissionManageMenuDaoEntity = menuRepository.save(accountPermissionManageMenuDaoEntity);

        //create 'permission manage' menu
        MenuDaoEntity permissionManageMenuDaoEntity = new MenuDaoEntity();
        permissionManageMenuDaoEntity.setParentId(accountPermissionManageMenuDaoEntity.getId());
        permissionManageMenuDaoEntity.setName("權限管理");
        permissionManageMenuDaoEntity.setPath("/home/permissionList");
        permissionManageMenuDaoEntity = menuRepository.save(permissionManageMenuDaoEntity);

        //add 'admin' role in 'permission manage' menu
        MenuRoleDaoEntity permissionMenuRoleDaoEntity = new MenuRoleDaoEntity();
        permissionMenuRoleDaoEntity.setMenuId(permissionManageMenuDaoEntity.getId());
        permissionMenuRoleDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        menuRoleRepository.save(permissionMenuRoleDaoEntity);

        //create 'role manage' menu
        MenuDaoEntity roleManageMenuDaoEntity = new MenuDaoEntity();
        roleManageMenuDaoEntity.setParentId(accountPermissionManageMenuDaoEntity.getId());
        roleManageMenuDaoEntity.setName("角色管理");
        roleManageMenuDaoEntity.setPath("/home/roleList");
        roleManageMenuDaoEntity = menuRepository.save(roleManageMenuDaoEntity);

        //add 'admin' role in 'role manage' menu
        MenuRoleDaoEntity roleMenuRoleDaoEntity = new MenuRoleDaoEntity();
        roleMenuRoleDaoEntity.setMenuId(roleManageMenuDaoEntity.getId());
        roleMenuRoleDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        menuRoleRepository.save(roleMenuRoleDaoEntity);

        //create 'menu manage' menu
        MenuDaoEntity menuManageMenuDaoEntity = new MenuDaoEntity();
        menuManageMenuDaoEntity.setParentId(accountPermissionManageMenuDaoEntity.getId());
        menuManageMenuDaoEntity.setName("選單管理");
        menuManageMenuDaoEntity.setPath("/home/menuList");
        menuManageMenuDaoEntity = menuRepository.save(menuManageMenuDaoEntity);

        //add 'admin' role in 'menu manage' menu
        MenuRoleDaoEntity menuMenuRoleDaoEntity = new MenuRoleDaoEntity();
        menuMenuRoleDaoEntity.setMenuId(menuManageMenuDaoEntity.getId());
        menuMenuRoleDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        menuRoleRepository.save(menuMenuRoleDaoEntity);

        //create 'account manage' menu
        MenuDaoEntity accountManageMenuDaoEntity = new MenuDaoEntity();
        accountManageMenuDaoEntity.setParentId(accountPermissionManageMenuDaoEntity.getId());
        accountManageMenuDaoEntity.setName("帳號管理");
        accountManageMenuDaoEntity.setPath("/home/accountList");
        accountManageMenuDaoEntity = menuRepository.save(accountManageMenuDaoEntity);

        //add 'admin' role in 'menu manage' menu
        MenuRoleDaoEntity accountMenuRoleDaoEntity = new MenuRoleDaoEntity();
        accountMenuRoleDaoEntity.setMenuId(accountManageMenuDaoEntity.getId());
        accountMenuRoleDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        menuRoleRepository.save(accountMenuRoleDaoEntity);

    }
}
