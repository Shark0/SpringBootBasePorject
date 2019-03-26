package com.shark.application.repository.account;

import com.shark.application.repository.account.dao.AccountDaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends JpaRepository<AccountDaoEntity, Long> {

    AccountDaoEntity findByAccount(String account);

    Page<AccountDaoEntity> findByNameContaining(String name, Pageable pageable);
}
