package com.shark.application.controller.permission;

import com.shark.application.controller.permission.pojo.PermissionDio;
import com.shark.application.controller.permission.pojo.PermissionEditDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.exception.DataProcessException;
import com.shark.application.exception.ResultGenerationException;
import com.shark.application.service.permission.*;
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
@RequestMapping("permission")
@Api(tags = "權限")
public class PermissionController {

    private final AddPermissionService addPermissionService;

    @ApiOperation(value = "新增權限", notes = "", produces = "application/json")
    @PostMapping
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDto<PermissionDo> addPermission(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody PermissionDio permissionDio) throws DataProcessException, ResultGenerationException {

        return addPermissionService.request(authAccountDo, permissionDio);
    }

    private final DeletePermissionService deletePermissionService;

    @ApiOperation(value = "刪除權限", notes = "-1：權限跟角色榜定，請先刪除榜定", produces = "application/json")
    @DeleteMapping("/{permissionId}")
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDto deletePermission(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "permissionId") Long permissionId) throws DataProcessException, ResultGenerationException {
        return deletePermissionService.request(authAccountDo, permissionId);
    }

    private final EditPermissionService editPermissionService;

    @ApiOperation(value = "編輯權限", notes = "", produces = "application/json")
    @PutMapping
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDto editPermission(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody PermissionEditDio permissionEditDio) throws DataProcessException, ResultGenerationException {
        return editPermissionService.request(authAccountDo, permissionEditDio);
    }

    private final GetPermissionListService getPermissionListService;

    @ApiOperation(value = "取得權限列表", notes = "", produces = "application/json")
    @GetMapping
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDto<List<PermissionDo>> getPermissionList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo) throws DataProcessException, ResultGenerationException {
        return getPermissionListService.request(authAccountDo, null);
    }

    private final GetPermissionService getPermissionService;

    @ApiOperation(value = "取得權限", notes = "", produces = "application/json")
    @GetMapping("/{permissionId}")
    @PreAuthorize("hasAuthority('permission')")
    public ResponseDto<PermissionDo> getPermission(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(value = "permissionId") Long permissionId) throws DataProcessException, ResultGenerationException {
        return getPermissionService.request(authAccountDo, permissionId);
    }
}
