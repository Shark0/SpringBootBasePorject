package com.shark.application.dao.repository.account;

import com.shark.application.dao.repository.account.pojo.AccountDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDo, Long> {

    AccountDo findByAccount(String account);

    Page<AccountDo> findByNameContaining(String name, Pageable pageable);
}
