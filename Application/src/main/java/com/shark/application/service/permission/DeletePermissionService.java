package com.shark.application.service.permission;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.role.RolePermissionRepository;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePermissionService extends BaseResponseService<Long> {

    private final RolePermissionRepository rolePermissionRepository;

    private final PermissionRepository permissionRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, Long permissionId) throws Exception {
        rolePermissionRepository.deleteByPermissionId(permissionId);
        permissionRepository.deleteById(permissionId);
        return null;
    }
}
