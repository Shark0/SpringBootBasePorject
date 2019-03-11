package com.shark.application.repository.account.dao;

import com.shark.application.repository.account.dao.id.AccountRoleIdEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "account_role")
@IdClass(AccountRoleIdEntity.class)
public class AccountRoleDaoEntity implements Serializable {

    @Id
    private Long accountId;

    @Id
    private Long roleId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
