package com.shark.application.controller;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.ResponseEntity;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.role.*;
import com.shark.application.util.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("role")
@Api(tags = "角色")
public class RoleController {

    @Autowired
    private AddRolePermissionService addRolePermissionService;

    @ApiOperation(value = "新增角色權限", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AddRolePermissionService.INPUT_ROLE_ID, value = "角色Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = AddRolePermissionService.INPUT_PERMISSION_ID, value = "權限Id", required = true, paramType = "query"),
    })
    @PostMapping("/addRolePermission")
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity addRolePermission(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "addRolePermission", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return addRolePermissionService.request(memberId, parameters);
    }

    @Autowired
    private AddRoleService addRoleService;
    @ApiOperation(value = "新增角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AddRoleService.INPUT_NAME, value = "角色名稱", required = true, paramType = "query")
    })
    @PostMapping("/addRole")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDataEntity<RoleDaoEntity> addRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "addRole", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return addRoleService.request(memberId, parameters);
    }


    @Autowired
    private DeleteRolePermissionService deleteRolePermissionService;

    @ApiOperation(value = "刪除角色權限", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DeleteRolePermissionService.INPUT_ROLE_ID, value = "角色Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = DeleteRolePermissionService.INPUT_PERMISSION_ID, value = "權限Id", required = true, paramType = "query")
    })
    @PostMapping("/deleteRolePermission")
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity deleteRolePermission(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "deleteRolePermission", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return deleteRolePermissionService.request(memberId, parameters);
    }

    @Autowired
    private DeleteRoleService deleteRoleService;
    @ApiOperation(value = "刪除角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DeleteRoleService.INPUT_ID, value = "角色Id", required = true, paramType = "query")
    })
    @PostMapping("/deleteRole")
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity deleteRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "deleteRole", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return deleteRoleService.request(memberId, parameters);
    }

    @Autowired
    private EditRoleService editRoleService;
    @ApiOperation(value = "編輯角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = EditRoleService.INPUT_ID, value = "角色id", required = true, paramType = "query"),
            @ApiImplicitParam(name = EditRoleService.INPUT_NAME, value = "角色名稱", required = true, paramType = "query")
    })
    @PostMapping("/editRole")
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity editRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "editRole", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return editRoleService.request(memberId, parameters);
    }

    @Autowired
    private GetRoleListService getRoleListService;
    @ApiOperation(value = "取得角色列表", notes = "", produces = "application/json")
    @GetMapping(value = "/getRoleList")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDataEntity<List<RoleDaoEntity>> getRoleList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getRoleList", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return getRoleListService.request(memberId, parameters);
    }

    @Autowired
    private SearchRolePermissionListService searchRolePermissionListService;

    @ApiOperation(value = "搜尋角色權限列表", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SearchRolePermissionListService.INPUT_ROLE_ID, value = "角色id", required = true, paramType = "query")
    })
    @PostMapping("/searchRolePermission")
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity searchRolePermission(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "searchRolePermission", false);
        return searchRolePermissionListService.request(memberId, parameters);
    }
}
