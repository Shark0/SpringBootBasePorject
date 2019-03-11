package com.shark.application.repository.account.dao.id;

import java.io.Serializable;

public class AccountRoleIdEntity implements Serializable {

    private Long accountId;

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
