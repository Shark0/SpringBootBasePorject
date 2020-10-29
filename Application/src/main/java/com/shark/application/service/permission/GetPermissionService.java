package com.shark.application.service.permission;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetPermissionService extends BaseQueryDataService<Long, PermissionDo, PermissionDo> {

    private final PermissionRepository permissionRepository;

    @Override
    protected PermissionDo process(AuthAccountDo authAccountDo, Long permissionId) throws Exception {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new WarningException("permission.does.not.exist"));
    }

    @Override
    protected ResponseDto<PermissionDo> generateResult(AuthAccountDo authAccountDo, PermissionDo permissionDo) {
        ResponseDto responseDataEntity = new ResponseDto();
        responseDataEntity.setResultData(permissionDo);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
