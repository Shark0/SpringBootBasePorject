package com.shark.application.controller;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.ResponseEntity;
import com.shark.application.dto.menu.MenuDtoEntity;
import com.shark.application.dto.menu.MenuRoleDtoEntity;
import com.shark.application.repository.menu.dao.MenuDaoEntity;
import com.shark.application.service.menu.*;
import com.shark.application.util.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("menu")
@Api(tags = "選單")
public class MenuController {

    @Autowired
    private AddMenuRoleService addMenuRoleService;

    @ApiOperation(value = "新增選單角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AddMenuRoleService.INPUT_MENU_ID, value = "選單id", required = true, paramType = "query"),
            @ApiImplicitParam(name = AddMenuRoleService.INPUT_ROLE_ID, value = "角色id", required = true, paramType = "query"),
    })
    @PostMapping("/addMenuRole")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseEntity addMenuRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "addMenuRole", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return addMenuRoleService.request(memberId, parameters);
    }

    @Autowired
    private AddMenuService addMenuService;

    @ApiOperation(value = "新增選單", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AddMenuService.INPUT_NAME, value = "名稱", required = true, paramType = "query"),
            @ApiImplicitParam(name = AddMenuService.INPUT_ICON_URL, value = "icon路徑", required = false, paramType = "query"),
            @ApiImplicitParam(name = AddMenuService.INPUT_PATH, value = "Redirect路徑", required = false, paramType = "query"),
            @ApiImplicitParam(name = AddMenuService.INPUT_SORT, value = "排序值，直越小月排越前面", required = true, paramType = "query"),
            @ApiImplicitParam(name = AddMenuService.INPUT_PARENT_ID, value = "父選單Id，如果沒有父選單就空值", required = false, paramType = "query")
    })
    @PostMapping("/addMenu")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDataEntity<MenuDtoEntity> addMenu(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "addMenu", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return addMenuService.request(memberId, parameters);
    }

    @Autowired
    private DeleteMenuRoleService deleteMenuRoleService;

    @ApiOperation(value = "刪除選單角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DeleteMenuRoleService.INPUT_MENU_ID, value = "選單id", required = true, paramType = "query"),
            @ApiImplicitParam(name = DeleteMenuRoleService.INPUT_ROLE_ID, value = "角色id", required = true, paramType = "query"),
    })
    @PostMapping("/deleteMenuRole")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseEntity deleteMenuRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "deleteMenuRole", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return deleteMenuRoleService.request(memberId, parameters);
    }

    @Autowired
    private DeleteMenuService deleteMenuService;

    @ApiOperation(value = "刪除選單", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DeleteMenuService.INPUT_ID, value = "選單id", required = true, paramType = "query"),
    })
    @PostMapping("/deleteMenu")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseEntity deleteMenu(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "deleteMenu", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return deleteMenuService.request(memberId, parameters);
    }

    @Autowired
    private EditMenuService editMenuService;

    @ApiOperation(value = "編輯選單", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = EditMenuService.INPUT_ID, value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = EditMenuService.INPUT_NAME, value = "名稱", required = false, paramType = "query"),
            @ApiImplicitParam(name = EditMenuService.INPUT_ICON_URL, value = "icon路徑", required = false, paramType = "query"),
            @ApiImplicitParam(name = EditMenuService.INPUT_PATH, value = "Redirect路徑", required = false, paramType = "query"),
            @ApiImplicitParam(name = EditMenuService.INPUT_SORT, value = "排序值，值越小月排越前面", required = false, paramType = "query"),
            @ApiImplicitParam(name = EditMenuService.INPUT_PARENT_ID, value = "父選單Id，如果沒有父選單就空值", required = false, paramType = "query"),
    })
    @PostMapping(value = "/editMenu")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseEntity editMenu(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "editMenu", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return editMenuService.request(memberId, parameters);
    }

    @Autowired
    private GetMainMenuListService getMainMenuListService;

    @ApiOperation(value = "取得主選單列表", notes = "", produces = "application/json")
    @GetMapping(value = "/getMainMenuList")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDataEntity<List<MenuDaoEntity>> getMainMenuList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getMainMenuList", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return getMainMenuListService.request(memberId, parameters);
    }

    @Autowired
    private GetMenuListService getMenuListService;

    @ApiOperation(value = "取得選單列表", notes = "", produces = "application/json")
    @GetMapping(value = "/getMenuList")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDataEntity<List<MenuDtoEntity>> getMenuList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getMenuList", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return getMenuListService.request(memberId, parameters);
    }

    @Autowired
    private GetMenuService getMenuService;

    @ApiOperation(value = "取得選單", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = GetMenuService.INPUT_ID, value = "id", required = true, paramType = "query"),
    })
    @GetMapping(value = "/getMenu")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDataEntity<MenuDtoEntity> getMenu(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getMenu", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return getMenuService.request(memberId, parameters);
    }

    @Autowired
    private GetMenuRoleListService getMenuRoleListService;

    @ApiOperation(value = "取得選單角色列表", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = GetMenuRoleListService.INPUT_MENU_ID, value = "選單id", required = true, paramType = "query")
    })
    @GetMapping(value = "/getMenuRoleList")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDataEntity<List<MenuRoleDtoEntity>> getMenuRoleList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getMenuRoleList", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return getMenuRoleListService.request(memberId, parameters);
    }
}
