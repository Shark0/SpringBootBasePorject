package com.shark.application;


import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.AccountRoleRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.repository.account.dao.AccountRoleDaoEntity;
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

        //add 'menu' permission to 'admin' role
        RolePermissionDaoEntity accountRolePermissionDaoEntity = new RolePermissionDaoEntity();
        accountRolePermissionDaoEntity.setRoleId(adminRoleDaoEntity.getId());
        accountRolePermissionDaoEntity.setPermissionId(accountPermissionDaoEntity.getId());
        rolePermissionRepository.save(accountRolePermissionDaoEntity);
    }
}
