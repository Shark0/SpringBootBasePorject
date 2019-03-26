package com.shark.application.controller;

import com.shark.application.dto.PageDtoEntity;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.ResponseEntity;
import com.shark.application.dto.account.AccountRoleDtoEntity;
import com.shark.application.dto.menu.MenuDtoEntity;
import com.shark.application.repository.account.dao.AccountDaoEntity;
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
    private GetAccountMenuListService getAccountMenuListService;

    @ApiOperation(value = "取得帳號選單列表", notes = "", produces = "application/json")
    @GetMapping("/getAccountMenuList")
    public ResponseDataEntity<List<MenuDtoEntity>> getAccountMenuList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getAccountMenuList", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return getAccountMenuListService.request(accountId, parameters);
    }


    @Autowired
    private EditAccountService editAccountService;

    @ApiOperation(value = "編輯帳號", notes = "-1：帳號不存在", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = EditAccountService.INPUT_ID, value = "帳號ID", paramType = "query"),
            @ApiImplicitParam(name = EditAccountService.INPUT_NAME, value = "名字", paramType = "query"),
            @ApiImplicitParam(name = EditAccountService.INPUT_PASSWORD, value = "密碼", paramType = "query")
    })
    @PostMapping("/editAccount")
    @PreAuthorize("hasAuthority('account')")
    public ResponseEntity editAccount(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "editAccount", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return editAccountService.request(accountId, parameters);
    }

    @Autowired
    private GetAccountRoleListService getAccountRoleListService;

    @ApiOperation(value = "取得帳號角色列表", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = GetAccountRoleListService.INPUT_ID, value = "帳號Id", required = true, paramType = "query")
    })
    @GetMapping("/getAccountRoleList")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDataEntity<List<AccountRoleDtoEntity>> getAccountRoleList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getAccountRoleList", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return getAccountRoleListService.request(accountId, parameters);
    }

    @Autowired
    private GetAccountService getAccountService;

    @ApiOperation(value = "取得帳號", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = GetAccountRoleListService.INPUT_ID, value = "帳號Id", required = true, paramType = "query")
    })
    @GetMapping("/getAccount")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDataEntity<AccountDaoEntity> getAccount(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "getAccount", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return getAccountService.request(accountId, parameters);
    }

    @Autowired
    private SearchAccountListService searchAccountListService;

    @ApiOperation(value = "搜尋帳號列表", notes = "", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = SearchAccountListService.INPUT_PAGE_NUMBER, value = "頁碼，從0開始", required = true, paramType = "query"),
            @ApiImplicitParam(name = SearchAccountListService.INPUT_PAGE_SIZE, value = "頁大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = SearchAccountListService.INPUT_KEYWORD, value = "關鍵字", paramType = "query")
    })
    @PostMapping("/searchAccountList")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDataEntity<PageDtoEntity<AccountDaoEntity>> searchAccountList(HttpServletRequest request) {
        HashMap<String, String> parameters =
                ServletUtil.generateServiceParameters(request, getClass(), "searchAccountList", false);
        Principal principal = request.getUserPrincipal();
        String accountId = principal.getName();
        return searchAccountListService.request(accountId, parameters);
    }
}


