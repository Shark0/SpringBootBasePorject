package com.shark.application.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDto extends MenuDo {

    private List<MenuDto> subMenuList;

    public MenuDto(MenuDo menuDo) {
        setId(menuDo.getId());
        setParentId(menuDo.getParentId());
        setName(menuDo.getName());
        setIconUrl(menuDo.getIconUrl());
        setPath(menuDo.getPath());
        setSort(menuDo.getSort());
    }
}
