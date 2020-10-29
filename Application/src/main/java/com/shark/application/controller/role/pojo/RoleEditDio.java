package com.shark.application.controller.role.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleEditDio implements Serializable {

    @ApiModelProperty(value = "角色Id", required = true)
    private Long id;

    @ApiModelProperty(value = "角色名稱", required = true)
    private String name;
}
