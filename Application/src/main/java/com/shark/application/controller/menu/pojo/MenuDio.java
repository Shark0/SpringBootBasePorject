package com.shark.application.controller.menu.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class MenuDio {

    @ApiModelProperty(value = "父菜單Id", required = false)
    private Long parentId;

    @NotNull
    @ApiModelProperty(value = "名稱", required = true)
    private String name;

    @ApiModelProperty(value = "路徑", required = true)
    private String path;

    @NotNull
    @ApiModelProperty(value = "排序", required = true)
    private Double sort;

    @ApiModelProperty(value = "圖示連結", required = false)
    private String iconUrl;
}
