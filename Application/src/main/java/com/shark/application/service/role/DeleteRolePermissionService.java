package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.role.pojo.RolePermissionDio;
import com.shark.application.dao.repository.role.RolePermissionRepository;
import com.shark.application.dao.repository.role.pojo.id.RolePermissionIdDo;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteRolePermissionService extends BaseResponseService<RolePermissionDio> {

    private final RolePermissionRepository rolePermissionRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, RolePermissionDio rolePermissionDio) throws Exception {
        RolePermissionIdDo rolePermissionIdDo = new RolePermissionIdDo();
        rolePermissionIdDo.setRoleId(rolePermissionDio.getRoleId());
        rolePermissionIdDo.setPermissionId(rolePermissionDio.getPermissionId());
        rolePermissionRepository.deleteById(rolePermissionIdDo);
        return null;
    }
}
