package com.shark.application.controller;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.ResponseEntity;
import com.shark.application.dto.menu.MenuDtoEntity;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.account.*;
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
@RequestMapping(value = "/account")
@Api(tags = "帳號")
public class AccountController {

    @Autowired
    private AddAccountRoleService addAccountRoleService;

    @ApiOperation(value = "新增帳號角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AddAccountRoleService.INPUT_ACCOUNT_ID, value = "帳號Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = AddAccountRoleService.INPUT_ROLE_ID, value = "角色Id", required = true, paramType = "query")
    })
    @PostMapping("/addAccountRole")
    @PreAuthorize("hasAuthority('account')")
    public ResponseEntity addAccountRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "addAccountRole", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return addAccountRoleService.request(accountId, parameters);
    }

    @Autowired
    private DeleteAccountRoleService deleteAccountRoleService;

    @ApiOperation(value = "刪除帳號角色", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = DeleteAccountRoleService.INPUT_ACCOUNT_ID, value = "帳號Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = DeleteAccountRoleService.INPUT_ROLE_ID, value = "角色Id", required = true, paramType = "query")
    })
    @PostMapping("/deleteAccountRole")
    @PreAuthorize("hasAuthority('account')")
    public ResponseEntity deleteAccountRole(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "deleteAccountRole", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return deleteAccountRoleService.request(accountId, parameters);
    }

    @Autowired
    private SearchAccountRoleListService searchAccountRoleListService;

    @ApiOperation(value = "搜尋帳號角色列表", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SearchAccountRoleListService.INPUT_ACCOUNT_ID, value = "帳號Id", required = true, paramType = "query")
    })
    @PostMapping("/searchAccountRoleList")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDataEntity<List<RoleDaoEntity>> searchAccountRoleList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "searchAccountRoleList", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return searchAccountRoleListService.request(accountId, parameters);
    }

    @Autowired
    private GetAccountMenuListService getAccountMenuListService;

    @ApiOperation(value = "取得帳號選單列表", notes = "", produces = "application/json")
    @GetMapping("/getAccountMenuList")
    public ResponseDataEntity<List<MenuDtoEntity>> searchAccountMenuList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "searchAccountMenuList", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return getAccountMenuListService.request(accountId, parameters);
    }


    @Autowired
    private EditAccountService editAccountService;

    @ApiOperation(value = "更新帳號", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = EditAccountService.INPUT_NAME, value = "名字", paramType = "query"),
            @ApiImplicitParam(name = EditAccountService.INPUT_PASSWORD, value = "密碼", paramType = "query")
    })
    @PostMapping("/editAccount")
    public ResponseEntity editAccount(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "editAccount", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return editAccountService.request(accountId, parameters);
    }
}


