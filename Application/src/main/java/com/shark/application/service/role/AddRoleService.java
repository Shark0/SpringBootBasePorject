package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.controller.role.pojo.RoleDio;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.dao.repository.role.RolePermissionRepository;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.dao.repository.role.pojo.RolePermissionDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.ListUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddRoleService extends BaseQueryDataService<RoleDio, RoleDo, RoleDo> {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final RolePermissionRepository rolePermissionRepository;

    @Transactional
    @Override
    protected RoleDo process(AuthAccountDo authAccountDo, RoleDio roleDio) throws Exception {
        RoleDo roleDo = new RoleDo();
        roleDo.setName(roleDio.getName());
        roleDo = roleRepository.save(roleDo);
        if(!ListUtil.isEmpty(roleDio.getPermissionIdList())) {
            for(Long permissionId: roleDio.getPermissionIdList()) {
                PermissionDo permissionDo = permissionRepository.findById(permissionId)
                        .orElseThrow(() -> new WarningException("permission.does.not.exist"));
                RolePermissionDo rolePermissionDo = new RolePermissionDo();
                rolePermissionDo.setRoleId(roleDo.getId());
                rolePermissionDo.setPermissionId(permissionDo.getId());
                rolePermissionRepository.save(rolePermissionDo);
            }
        }
        return roleDo;
    }

    @Override
    protected ResponseDto<RoleDo> generateResult(AuthAccountDo authAccountDo, RoleDo roleDo) {
        ResponseDto responseDataEntity = new ResponseDto();
        responseDataEntity.setResultData(roleDo);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
