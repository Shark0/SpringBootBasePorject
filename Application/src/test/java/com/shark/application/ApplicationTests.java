package com.shark.application;


import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.AccountRoleRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.dao.repository.account.pojo.AccountRoleDo;
import com.shark.application.dao.repository.menu.MenuRepository;
import com.shark.application.dao.repository.menu.MenuRoleRepository;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import com.shark.application.dao.repository.menu.pojo.MenuRoleDo;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.dao.repository.role.RolePermissionRepository;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.dao.repository.role.pojo.RolePermissionDo;
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
        AccountDo accountDo = accountRepository.findByAccount(account);
        if(accountDo != null) {
            return;
        }
        accountDo = new AccountDo();
        accountDo.setName("root");
        accountDo.setAccount("root");
        accountDo.setPassword("root");
        accountDo = accountRepository.save(accountDo);
        long accountId = accountDo.getId();
        System.out.println("root account id: " + accountId);

        //create 'admin' role
        RoleDo adminRoleDo = new RoleDo();
        adminRoleDo.setName("admin");
        adminRoleDo = roleRepository.save(adminRoleDo);
        System.out.println("admin role id: " + adminRoleDo.getId());

        //add 'admin' role to 'root' account
        AccountRoleDo accountRoleDo = new AccountRoleDo();
        accountRoleDo.setAccountId(accountDo.getId());
        accountRoleDo.setRoleId(adminRoleDo.getId());
        accountRoleRepository.save(accountRoleDo);

        //create 'permission' permission
        PermissionDo permissionPermissionDo = new PermissionDo();
        permissionPermissionDo.setName("權限");
        permissionPermissionDo.setCode("permission");
        permissionPermissionDo = permissionRepository.save(permissionPermissionDo);
        System.out.println("permission permission id: " + permissionPermissionDo.getId());

        //add 'permission' permission to 'admin' role
        RolePermissionDo permissionRolePermissionDo = new RolePermissionDo();
        permissionRolePermissionDo.setRoleId(adminRoleDo.getId());
        permissionRolePermissionDo.setPermissionId(permissionPermissionDo.getId());
        rolePermissionRepository.save(permissionRolePermissionDo);

        //create 'role' permission
        PermissionDo rolePermissionDo = new PermissionDo();
        rolePermissionDo.setName("角色");
        rolePermissionDo.setCode("role");
        rolePermissionDo = permissionRepository.save(rolePermissionDo);
        System.out.println("role permission id: " + rolePermissionDo.getId());

        //add 'role' permission to 'admin' role
        RolePermissionDo roleRolePermissionDo = new RolePermissionDo();
        roleRolePermissionDo.setRoleId(adminRoleDo.getId());
        roleRolePermissionDo.setPermissionId(rolePermissionDo.getId());
        rolePermissionRepository.save(roleRolePermissionDo);

        //create 'menu' permission
        PermissionDo menuPermissionDo = new PermissionDo();
        menuPermissionDo.setName("選單");
        menuPermissionDo.setCode("menu");
        menuPermissionDo = permissionRepository.save(menuPermissionDo);
        System.out.println("menu permission id: " + menuPermissionDo.getId());

        //add 'menu' permission to 'admin' role
        RolePermissionDo menuRolePermissionDo = new RolePermissionDo();
        menuRolePermissionDo.setRoleId(adminRoleDo.getId());
        menuRolePermissionDo.setPermissionId(menuPermissionDo.getId());
        rolePermissionRepository.save(menuRolePermissionDo);

        //create 'account' permission
        PermissionDo accountPermissionDo = new PermissionDo();
        accountPermissionDo.setName("帳號");
        accountPermissionDo.setCode("account");
        accountPermissionDo = permissionRepository.save(accountPermissionDo);
        System.out.println("account permission id: " + accountPermissionDo.getId());

        //add 'account' permission to 'admin' role
        RolePermissionDo accountRolePermissionDo = new RolePermissionDo();
        accountRolePermissionDo.setRoleId(adminRoleDo.getId());
        accountRolePermissionDo.setPermissionId(accountPermissionDo.getId());
        rolePermissionRepository.save(accountRolePermissionDo);

        //create home menu
        MenuDo homeMenuDo = new MenuDo();
        homeMenuDo.setParentId((long) 0);
        homeMenuDo.setName("首頁");
        homeMenuDo.setPath("/home/main");
        menuRepository.save(homeMenuDo);

        //create permission manage menu
        MenuDo accountPermissionManageMenuDo = new MenuDo();
        accountPermissionManageMenuDo.setParentId((long) 0);
        accountPermissionManageMenuDo.setName("帳號權限管理");
        accountPermissionManageMenuDo = menuRepository.save(accountPermissionManageMenuDo);

        //create 'permission manage' menu
        MenuDo permissionManageMenuDo = new MenuDo();
        permissionManageMenuDo.setParentId(accountPermissionManageMenuDo.getId());
        permissionManageMenuDo.setName("權限管理");
        permissionManageMenuDo.setPath("/home/permissionList");
        permissionManageMenuDo = menuRepository.save(permissionManageMenuDo);

        //add 'admin' role in 'permission manage' menu
        MenuRoleDo permissionMenuRoleDo = new MenuRoleDo();
        permissionMenuRoleDo.setMenuId(permissionManageMenuDo.getId());
        permissionMenuRoleDo.setRoleId(adminRoleDo.getId());
        menuRoleRepository.save(permissionMenuRoleDo);

        //create 'role manage' menu
        MenuDo roleManageMenuDo = new MenuDo();
        roleManageMenuDo.setParentId(accountPermissionManageMenuDo.getId());
        roleManageMenuDo.setName("角色管理");
        roleManageMenuDo.setPath("/home/roleList");
        roleManageMenuDo = menuRepository.save(roleManageMenuDo);

        //add 'admin' role in 'role manage' menu
        MenuRoleDo roleMenuRoleDo = new MenuRoleDo();
        roleMenuRoleDo.setMenuId(roleManageMenuDo.getId());
        roleMenuRoleDo.setRoleId(adminRoleDo.getId());
        menuRoleRepository.save(roleMenuRoleDo);

        //create 'menu manage' menu
        MenuDo menuManageMenuDo = new MenuDo();
        menuManageMenuDo.setParentId(accountPermissionManageMenuDo.getId());
        menuManageMenuDo.setName("選單管理");
        menuManageMenuDo.setPath("/home/menuList");
        menuManageMenuDo = menuRepository.save(menuManageMenuDo);

        //add 'admin' role in 'menu manage' menu
        MenuRoleDo menuMenuRoleDo = new MenuRoleDo();
        menuMenuRoleDo.setMenuId(menuManageMenuDo.getId());
        menuMenuRoleDo.setRoleId(adminRoleDo.getId());
        menuRoleRepository.save(menuMenuRoleDo);

        //create 'account manage' menu
        MenuDo accountManageMenuDo = new MenuDo();
        accountManageMenuDo.setParentId(accountPermissionManageMenuDo.getId());
        accountManageMenuDo.setName("帳號管理");
        accountManageMenuDo.setPath("/home/accountList");
        accountManageMenuDo = menuRepository.save(accountManageMenuDo);

        //add 'admin' role in 'menu manage' menu
        MenuRoleDo accountMenuRoleDo = new MenuRoleDo();
        accountMenuRoleDo.setMenuId(accountManageMenuDo.getId());
        accountMenuRoleDo.setRoleId(adminRoleDo.getId());
        menuRoleRepository.save(accountMenuRoleDo);

    }
}
