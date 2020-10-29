package com.shark.application.service.permission;

import com.shark.application.controller.permission.pojo.PermissionDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddPermissionService extends BaseQueryDataService<PermissionDio, PermissionDo, PermissionDo> {

    private final PermissionRepository permissionRepository;

    @Override
    protected PermissionDo process(AuthAccountDo authAccountDo, PermissionDio permissionDio) throws Exception {
        PermissionDo permissionDaoEntity = new PermissionDo();
        permissionDaoEntity.setName(permissionDio.getName());
        permissionDaoEntity.setCode(permissionDio.getCode());
        permissionDaoEntity = permissionRepository.save(permissionDaoEntity);
        return permissionDaoEntity;
    }

    @Override
    protected ResponseDto<PermissionDo> generateResult(AuthAccountDo authAccountDo, PermissionDo permissionDo) {
        ResponseDto<PermissionDo> responseDto = new ResponseDto();
        responseDto.setResultData(permissionDo);
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
