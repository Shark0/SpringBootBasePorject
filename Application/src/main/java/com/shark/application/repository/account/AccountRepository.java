package com.shark.application.repository.account;

import com.shark.application.repository.account.dao.AccountDaoEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountDaoEntity, Long> {

    AccountDaoEntity findByAccount(String account);
}
