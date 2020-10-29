package com.shark.application.service.account;


import com.shark.application.controller.account.pojo.AccountRoleDto;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAccountRoleListService extends BaseQueryDataService<Long, List<AccountRoleDto>, List<AccountRoleDto>> {

    private final RoleRepository roleRepository;

    @Override
    protected List<AccountRoleDto> process(AuthAccountDo authAccountDo, Long accountId) throws Exception {
        List<RoleDo> accountRoleList = roleRepository.findByAccountId(accountId);
        HashSet<Long> accountRoleHashSet = new HashSet<>();
        for (RoleDo roleDo : accountRoleList) {
            accountRoleHashSet.add(roleDo.getId());
        }
        List<RoleDo> roleDaoEntityList = roleRepository.findAll();
        List<AccountRoleDto> accountRoleDtoList = new ArrayList<>();
        for (RoleDo roleDo : roleDaoEntityList) {
            AccountRoleDto accountRoleDto = new AccountRoleDto();
            accountRoleDto.setRoleId(roleDo.getId());
            accountRoleDto.setRoleName(roleDo.getName());
            accountRoleDto.setAdd(accountRoleHashSet.contains(roleDo.getId()));
            accountRoleDtoList.add(accountRoleDto);
        }
        return accountRoleDtoList;
    }

    @Override
    protected ResponseDto<List<AccountRoleDto>> generateResult(AuthAccountDo authAccountDo, List<AccountRoleDto> accountRoleDtoList) {
        ResponseDto<List<AccountRoleDto>> responseDto = new ResponseDto();
        responseDto.setResultData(accountRoleDtoList);
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
