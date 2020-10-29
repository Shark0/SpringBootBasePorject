package com.shark.application.service.permission;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetPermissionListService extends BaseQueryDataService<Void, List<PermissionDo>, List<PermissionDo>> {

    private final PermissionRepository permissionRepository;

    @Override
    protected List<PermissionDo> process(AuthAccountDo authAccountDo, Void unused) throws Exception {
        return permissionRepository.findAll();
    }

    @Override
    protected ResponseDto<List<PermissionDo>> generateResult(AuthAccountDo authAccountDo, List<PermissionDo> permissionDoList) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultData(permissionDoList);
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
