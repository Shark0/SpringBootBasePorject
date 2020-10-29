package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetRoleService extends BaseQueryDataService<Long, RoleDo, RoleDo> {

    private final RoleRepository roleRepository;

    @Override
    protected RoleDo process(AuthAccountDo authAccountDo, Long roleId) throws Exception {
        return roleRepository.findById(roleId).orElseThrow(() -> new WarningException("role.does.not.exist"));
    }

    @Override
    protected ResponseDto<RoleDo> generateResult(AuthAccountDo authAccountDo, RoleDo roleDo) {
        ResponseDto<RoleDo> responseDataEntity = new ResponseDto();
        responseDataEntity.setResultData(roleDo);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
