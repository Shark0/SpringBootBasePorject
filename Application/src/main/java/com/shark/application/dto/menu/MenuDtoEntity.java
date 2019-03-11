package com.shark.application.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shark.application.repository.menu.dao.MenuDaoEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDtoEntity extends MenuDaoEntity {

    private List<MenuDtoEntity> subMenuList;

    public MenuDtoEntity(MenuDaoEntity menuDaoEntity) {
        setId(menuDaoEntity.getId());
        setParentId(menuDaoEntity.getParentId());
        setName(menuDaoEntity.getName());
        setIconUrl(menuDaoEntity.getIconUrl());
        setPath(menuDaoEntity.getPath());
        setSort(menuDaoEntity.getSort());
    }

    public List<MenuDtoEntity> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<MenuDtoEntity> subMenuList) {
        this.subMenuList = subMenuList;
    }
}
