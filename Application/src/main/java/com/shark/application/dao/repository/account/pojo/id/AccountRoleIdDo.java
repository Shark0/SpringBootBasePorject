package com.shark.application.dao.repository.account.pojo.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountRoleIdDo implements Serializable {

    private Long accountId;

    private Long roleId;
}
