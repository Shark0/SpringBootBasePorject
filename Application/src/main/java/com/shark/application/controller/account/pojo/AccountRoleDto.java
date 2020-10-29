package com.shark.application.controller.account.pojo;

import lombok.Data;

@Data
public class AccountRoleDto {
    private Long roleId;

    private String roleName;

    private boolean isAdd;
}
