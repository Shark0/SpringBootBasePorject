package com.shark.application.controller;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.ResponseEntity;
import com.shark.application.repository.permission.dao.PermissionDaoEntity;
import com.shark.application.service.permission.AddPermissionService;
import com.shark.application.service.permission.DeletePermissionService;
import com.shark.application.service.permission.EditPermissionService;
import com.shark.application.service.permission.GetPermissionListService;
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
@RequestMapping("permission")
@Api(tags = "權限")
public class PermissionController {

    @Autowired
    private AddPermissionService addPermissionService;

    @ApiOperation(value = "新增權限", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AddPermissionService.INPUT_NAME, value = "名稱", required = true, paramType = "query"),
            @ApiImplicitParam(name = AddPermissionService.INPUT_CODE, value = "代碼 EX：permission", required = true, paramType = "query"),
    })
    @PostMapping("/addPermission")
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDataEntity<PermissionDaoEntity> addPermission(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "addPermission", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return addPermissionService.request(memberId, parameters);
    }

    @Autowired
    private DeletePermissionService deletePermissionService;

    @ApiOperation(value = "刪除權限", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DeletePermissionService.INPUT_ID, value = "權限ID", required = true, paramType = "query")
    })
    @PostMapping("/deletePermission")
    @PreAuthorize("hasAuthority('permission')")
    public ResponseEntity deletePermission(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "deletePermission", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return deletePermissionService.request(memberId, parameters);
    }

    @Autowired
    private EditPermissionService editPermissionService;

    @ApiOperation(value = "編輯權限", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = EditPermissionService.INPUT_ID, value = "權限ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = EditPermissionService.INPUT_NAME, value = "名稱", paramType = "query"),
            @ApiImplicitParam(name = EditPermissionService.INPUT_CODE, value = "代碼 EX：permission", paramType = "query"),
    })
    @PostMapping("/editPermission")
    @PreAuthorize("hasAuthority('permission')")
    public ResponseEntity editPermission(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "editPermission", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return editPermissionService.request(memberId, parameters);
    }

    @Autowired
    private GetPermissionListService getPermissionListService;

    @ApiOperation(value = "取得權限列表", notes = "", produces = "application/json")
    @GetMapping("/getPermissionList")
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDataEntity<List<PermissionDaoEntity>> getPermissionList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getPermissionList", false);
        Principal principal = request.getUserPrincipal();
        String memberId = principal.getName();
        return getPermissionListService.request(memberId, parameters);
    }

}
