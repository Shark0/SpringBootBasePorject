package com.shark.application.controller.menu.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
public class MenuRoleDio implements Serializable {

    @NotNull
    @ApiModelProperty(value = "菜單Id", required = true)
    private Long menuId;

    @NotNull
    @ApiModelProperty(value = "角色Id", required = true)
    private Long roleId;
}
