package com.shark.application.repository.account;

import com.shark.application.repository.account.dao.AccountRoleDaoEntity;
import com.shark.application.repository.account.dao.id.AccountRoleIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleRepository extends JpaRepository<AccountRoleDaoEntity, AccountRoleIdEntity> {
}
