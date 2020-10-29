package com.shark.application.controller.permission.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionEditDio implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "名稱", required = true)
    private String name;

    @ApiModelProperty(value = "代碼", required = true)
    private String code;
}
