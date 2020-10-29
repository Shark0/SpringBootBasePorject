package com.shark.application.controller.menu;

import com.shark.application.controller.menu.pojo.MenuDio;
import com.shark.application.controller.menu.pojo.MenuEditDio;
import com.shark.application.controller.menu.pojo.MenuRoleDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import com.shark.application.dto.menu.MenuDto;
import com.shark.application.dto.menu.MenuRoleDto;
import com.shark.application.exception.DataProcessException;
import com.shark.application.exception.ResultGenerationException;
import com.shark.application.service.menu.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("menu")
@Api(tags = "選單")
public class MenuController {

    private final AddMenuService addMenuService;

    @ApiOperation(value = "新增選單", notes = "", produces = "application/json")
    @PostMapping
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto<MenuDto> addMenu(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody MenuDio menuDio) throws DataProcessException, ResultGenerationException {
        return addMenuService.request(authAccountDo, menuDio);
    }

    private final AddMenuRoleService addMenuRoleService;

    @ApiOperation(value = "新增選單角色", notes = "", produces = "application/json")
    @PostMapping("/role")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto addMenuRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody MenuRoleDio menuRoleDio) throws DataProcessException, ResultGenerationException {
        return addMenuRoleService.request(authAccountDo, menuRoleDio);
    }

    private final DeleteMenuRoleService deleteMenuRoleService;

    @ApiOperation(value = "刪除選單角色", notes = "", produces = "application/json")
    @DeleteMapping("/{menuId}/role/{roleId}")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto deleteMenuRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "menuId") Long menuId,
            @PathVariable(value = "roleId") Long roleId
            ) throws DataProcessException, ResultGenerationException {
        MenuRoleDio menuRoleDio = MenuRoleDio.builder().menuId(menuId).roleId(roleId).build();
        return deleteMenuRoleService.request(authAccountDo, menuRoleDio);
    }

    private final DeleteMenuService deleteMenuService;

    @ApiOperation(value = "刪除選單", notes = "", produces = "application/json")

    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto deleteMenu(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "menuId") Long menuId) throws DataProcessException, ResultGenerationException {
        return deleteMenuService.request(authAccountDo, menuId);
    }

    private final EditMenuService editMenuService;

    @ApiOperation(value = "編輯選單", notes = "", produces = "application/json")
    @PutMapping
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto editMenu(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            MenuEditDio menuEditDio
    ) throws DataProcessException, ResultGenerationException {
        return editMenuService.request(authAccountDo, menuEditDio);
    }

    private final GetMainMenuListService getMainMenuListService;

    @ApiOperation(value = "取得主選單列表", notes = "", produces = "application/json")
    @GetMapping(value = "/main")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto<List<MenuDo>> getMainMenuList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo) throws DataProcessException, ResultGenerationException {
        return getMainMenuListService.request(authAccountDo, null);
    }

    private final GetMenuListService getMenuListService;

    @ApiOperation(value = "取得選單列表", notes = "", produces = "application/json")
    @GetMapping
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto<List<MenuDto>> getMenuList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo) throws DataProcessException, ResultGenerationException {
        return getMenuListService.request(authAccountDo, null);
    }

    private final GetMenuService getMenuService;

    @ApiOperation(value = "取得選單", notes = "", produces = "application/json")
    @GetMapping(value = "/{menuId}")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto<MenuDto> getMenu(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "menuId") Long menuId
    ) throws DataProcessException, ResultGenerationException {
        return getMenuService.request(authAccountDo, menuId);
    }

    private final GetMenuRoleListService getMenuRoleListService;

    @ApiOperation(value = "取得選單角色列表", notes = "", produces = "application/json")
    @GetMapping(value = "/{menuId}/role")
    @PreAuthorize("hasAuthority('menu')")
    public ResponseDto<List<MenuRoleDto>> getMenuRoleList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "menuId") Long menuId) throws DataProcessException, ResultGenerationException {
        return getMenuRoleListService.request(authAccountDo, menuId);
    }
}
