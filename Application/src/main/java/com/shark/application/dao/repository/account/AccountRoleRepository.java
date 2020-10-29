package com.shark.application.dao.repository.account;

import com.shark.application.dao.repository.account.pojo.AccountRoleDo;
import com.shark.application.dao.repository.account.pojo.id.AccountRoleIdDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleRepository extends JpaRepository<AccountRoleDo, AccountRoleIdDo> {
}
