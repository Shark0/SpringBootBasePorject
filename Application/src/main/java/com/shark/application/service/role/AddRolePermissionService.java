package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.role.pojo.RolePermissionDio;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.dao.repository.role.RolePermissionRepository;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.dao.repository.role.pojo.RolePermissionDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AddRolePermissionService extends BaseResponseService<RolePermissionDio> {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final RolePermissionRepository rolePermissionRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, RolePermissionDio rolePermissionDio) throws Exception {
        RoleDo roleDaoEntity = roleRepository.findById(rolePermissionDio.getRoleId())
                .orElseThrow(() -> new WarningException("role.does.not.exist"));
        PermissionDo permissionDo = permissionRepository.findById(rolePermissionDio.getPermissionId())
                .orElseThrow(() -> new WarningException("permission.does.not.exist"));

        RolePermissionDo rolePermissionDo = new RolePermissionDo();
        rolePermissionDo.setRoleId(roleDaoEntity.getId());
        rolePermissionDo.setPermissionId(permissionDo.getId());
        rolePermissionRepository.save(rolePermissionDo);
        return null;
    }
}
