package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.role.pojo.RoleEditDio;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditRoleService extends BaseResponseService<RoleEditDio> {

    private final RoleRepository roleRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, RoleEditDio roleEditDio) throws Exception {
        RoleDo roleDo = roleRepository.findById(roleEditDio.getId())
                .orElseThrow(() -> new WarningException("role.does.not.exist"));
        roleDo.setName(roleEditDio.getName());
        roleRepository.save(roleDo);
        return null;
    }
}
