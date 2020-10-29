package com.shark.application.controller.role.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleDio implements Serializable {
    @ApiModelProperty(value = "角色 id", required = true)
    private String name;

    @ApiModelProperty(value = "權限 id 列表", required = true)
    private List<Long> permissionIdList;
}
