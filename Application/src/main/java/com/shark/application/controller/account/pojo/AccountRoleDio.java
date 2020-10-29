package com.shark.application.controller.account.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
public class AccountRoleDio implements Serializable {
    @NotNull
    @ApiModelProperty(value = "帳號Id", required = true)
    private Long accountId;

    @NotNull
    @ApiModelProperty(value = "角色Id", required = true)
    private Long ruleId;
}
