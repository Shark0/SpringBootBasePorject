package com.shark.application.controller.role.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RolePermissionDio implements Serializable {
    @ApiModelProperty(value = "角色 id", required = true)
    private Long roleId;

    @ApiModelProperty(value = "權限 id", required = true)
    private Long permissionId;
}
