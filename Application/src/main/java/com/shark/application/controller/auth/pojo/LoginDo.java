package com.shark.application.controller.auth.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginDo implements Serializable {
    @NotEmpty
    @ApiModelProperty(value = "帳號", required = true)
    private String account;

    @NotEmpty
    @ApiModelProperty(value = "密碼", required = true)
    private String password;
}
