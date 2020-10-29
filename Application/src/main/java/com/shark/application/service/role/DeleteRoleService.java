package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.role.RolePermissionRepository;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteRoleService extends BaseResponseService<Long> {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, Long roleId) throws Exception {
        rolePermissionRepository.deleteByRoleId(roleId);
        roleRepository.deleteById(roleId);
        return null;
    }
}
