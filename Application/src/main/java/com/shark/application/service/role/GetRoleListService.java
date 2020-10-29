package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRoleListService extends BaseQueryDataService<Void, List<RoleDo>, List<RoleDo>> {

    private final RoleRepository roleRepository;

    @Override
    protected List<RoleDo> process(AuthAccountDo authAccountDo, Void unused) throws Exception {
        return roleRepository.findAll();
    }

    @Override
    protected ResponseDto<List<RoleDo>> generateResult(AuthAccountDo authAccountDo, List<RoleDo> roleDoList) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultData(roleDoList);
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
