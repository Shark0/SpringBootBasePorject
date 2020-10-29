package com.shark.application.controller.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.controller.role.pojo.RoleDio;
import com.shark.application.controller.role.pojo.RoleEditDio;
import com.shark.application.controller.role.pojo.RolePermissionDio;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.dto.role.RolePermissionDto;
import com.shark.application.exception.DataProcessException;
import com.shark.application.exception.ResultGenerationException;
import com.shark.application.service.role.*;
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
@RequestMapping("role")
@Api(tags = "角色")
public class RoleController {

    private final AddRolePermissionService addRolePermissionService;

    @ApiOperation(value = "新增角色權限")
    @PostMapping("/permission")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto addRolePermission(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody RolePermissionDio rolePermissionDio) throws DataProcessException, ResultGenerationException {
        return addRolePermissionService.request(authAccountDo, rolePermissionDio);
    }

    private final AddRoleService addRoleService;

    @ApiOperation(value = "新增角色")
    @PostMapping
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto<RoleDo> addRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody RoleDio roleDio) throws DataProcessException, ResultGenerationException {
        return addRoleService.request(authAccountDo, roleDio);
    }

    private final DeleteRolePermissionService deleteRolePermissionService;

    @ApiOperation(value = "刪除角色權限")
    @DeleteMapping("/{roleId}/permission/{permissionId}")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto deleteRolePermission(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(name = "roleId") Long roleId,
            @PathVariable(name = "permissionId") Long permissionId) throws DataProcessException, ResultGenerationException {
        RolePermissionDio permissionDio = RolePermissionDio.builder().roleId(roleId).permissionId(permissionId).build();
        return deleteRolePermissionService.request(authAccountDo, permissionDio);
    }

    private final DeleteRoleService deleteRoleService;

    @ApiOperation(value = "刪除角色")
    @DeleteMapping("/{roleId}}")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto deleteRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(name = "roleId") Long roleId
    ) throws DataProcessException, ResultGenerationException {
        return deleteRoleService.request(authAccountDo, roleId);
    }

    private final EditRoleService editRoleService;
    @ApiOperation(value = "編輯角色")
    @PutMapping
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto editRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @Valid @RequestBody RoleEditDio roleEditDio) throws DataProcessException, ResultGenerationException {
        return editRoleService.request(authAccountDo, roleEditDio);
    }

    private final GetRoleListService getRoleListService;

    @ApiOperation(value = "取得角色列表")
    @GetMapping(value = "/getRoleList")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto<List<RoleDo>> getRoleList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo) throws DataProcessException, ResultGenerationException {
        return getRoleListService.request(authAccountDo, null);
    }

    private final GetRoleService getRoleService;

    @ApiOperation(value = "取得角色")
    @GetMapping(value = "/{roleId}}")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto<RoleDo> getRole(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(name = "roleId") Long roleId) throws DataProcessException, ResultGenerationException {
        return getRoleService.request(authAccountDo, roleId);
    }

    private final GetRolePermissionListService getRolePermissionListService;

    @ApiOperation(value = "搜尋角色權限列表", notes = "", produces = "application/json")
    @GetMapping("/{roleId}/permission")
    @PreAuthorize("hasAuthority('role')")
    public ResponseDto<List<RolePermissionDto>> getRolePermissionList(
            @ApiIgnore @AuthenticationPrincipal AuthAccountDo authAccountDo,
            @PathVariable(name = "roleId") Long roleId) throws DataProcessException, ResultGenerationException {
        return getRolePermissionListService.request(authAccountDo, roleId);
    }
}
