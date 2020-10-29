package com.shark.application.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuRoleDto {

    private Long roleId;

    private String roleName;

    private boolean isAdd;
}
