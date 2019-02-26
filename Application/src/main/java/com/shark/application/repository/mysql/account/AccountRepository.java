package com.shark.application.repository.mysql.account;

import com.shark.application.repository.mysql.account.dao.AccountDaoEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountDaoEntity, Long> {

    public AccountDaoEntity findByAccount(String account);
}
