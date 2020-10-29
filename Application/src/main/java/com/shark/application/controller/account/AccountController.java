package com.shark.application.controller.account;

import com.shark.application.controller.account.pojo.AccountDio;
import com.shark.application.controller.account.pojo.AccountRoleDio;
import com.shark.application.controller.account.pojo.AccountRoleDto;
import com.shark.application.controller.account.pojo.AccountSearchDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.PageDto;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.dto.menu.MenuDto;
import com.shark.application.exception.DataProcessException;
import com.shark.application.exception.ResultGenerationException;
import com.shark.application.service.account.*;
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
@RequestMapping(value = "/account")
@Api(tags = "帳號")
public class AccountController {

    private final AddAccountRoleService addAccountRoleService;

    @ApiOperation(value = "新增帳號角色", notes = "", produces = "application/json")
    @PostMapping("/role")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto addAccountRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody AccountRoleDio accountRoleDio
    ) throws DataProcessException, ResultGenerationException {

        return addAccountRoleService.request(authAccountDo, accountRoleDio);
    }

    private final DeleteAccountRoleService deleteAccountRoleService;

    @ApiOperation(value = "刪除帳號角色", notes = "", produces = "application/json")
    @DeleteMapping("/{accountId}/role/{roleId}")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto deleteAccountRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "accountId") Long accountId,
            @PathVariable(value = "roleId") Long roleId) throws DataProcessException, ResultGenerationException {
        AccountRoleDio accountRoleDio = AccountRoleDio.builder().accountId(accountId).ruleId(roleId).build();
        return deleteAccountRoleService.request(authAccountDo, accountRoleDio);
    }

    private final GetAccountMenuListService getAccountMenuListService;

    @ApiOperation(value = "取得帳號選單列表", notes = "", produces = "application/json")
    @GetMapping("{accountId}/menu")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto<List<MenuDto>> getAccountMenuList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "accountId") Long accountId
    ) throws DataProcessException, ResultGenerationException {

        return getAccountMenuListService.request(authAccountDo, accountId);
    }

    private final EditAccountService editAccountService;

    @ApiOperation(value = "編輯帳號", notes = "-1：帳號不存在", produces = "application/json")
    @PutMapping
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto editAccount(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody AccountDio accountDio
    ) throws DataProcessException, ResultGenerationException {
        return editAccountService.request(authAccountDo, accountDio);
    }

    private final GetAccountRoleListService getAccountRoleListService;

    @ApiOperation(value = "取得帳號角色列表", notes = "", produces = "application/json")
    @GetMapping("{accountId}/role")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto<List<AccountRoleDto>> getAccountRoleList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "accountId") Long accountId) throws DataProcessException, ResultGenerationException {
        return getAccountRoleListService.request(authAccountDo, accountId);
    }

    private final GetAccountService getAccountService;

    @ApiOperation(value = "取得帳號", notes = "", produces = "application/json")
    @GetMapping("/{accountId}")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto<AccountDo> getAccount(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "accountId") Long accountId) throws DataProcessException, ResultGenerationException {
        return getAccountService.request(authAccountDo, accountId);
    }

    private final SearchAccountListService searchAccountListService;

    @ApiOperation(value = "搜尋帳號列表", notes = "", produces = "application/json")
    @GetMapping("/page/{pageNumber}")
    @PreAuthorize("hasAuthority('account')")
    public ResponseDto<PageDto<AccountDo>> searchAccountList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "pageNumber") Integer pageNumber,
            @RequestParam("keyword") String keyword
    ) throws DataProcessException, ResultGenerationException {
        AccountSearchDio accountSearchDio =
                AccountSearchDio.builder().pageNumber(pageNumber).keyword(keyword).build();
        return searchAccountListService.request(authAccountDo, accountSearchDio);
    }
}


