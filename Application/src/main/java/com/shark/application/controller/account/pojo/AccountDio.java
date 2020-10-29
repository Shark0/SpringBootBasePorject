package com.shark.application.controller.account.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AccountDio implements Serializable {

    @ApiModelProperty(value = "帳號Id", required = true)
    private Long id;

    @ApiModelProperty(value = "名稱", required = false)
    private String name;
    
    @ApiModelProperty(value = "password", required = false)
    private String password;
}
