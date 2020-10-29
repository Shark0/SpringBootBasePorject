package com.shark.application.service.role;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.dto.role.RolePermissionDto;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRolePermissionListService extends
        BaseQueryDataService<Long, List<RolePermissionDto>, List<RolePermissionDto>> {

    private final PermissionRepository permissionRepository;


    @Override
    protected List<RolePermissionDto> process(AuthAccountDo authAccountDo, Long roleId) throws Exception {
        List<PermissionDo> rolePermissionList = permissionRepository.findByRoleId(roleId);
        HashSet<Long> rolePermissionHashSet = new HashSet<>();
        for (PermissionDo permissionDo : rolePermissionList) {
            rolePermissionHashSet.add(permissionDo.getId());
        }
        List<PermissionDo> permissionList = permissionRepository.findAll();
        List<RolePermissionDto> rolePermissionDtoList = new ArrayList<>();
        for (PermissionDo permissionDo : permissionList) {
            RolePermissionDto rolePermissionDto = new RolePermissionDto();
            rolePermissionDto.setPermissionId(permissionDo.getId());
            rolePermissionDto.setPermissionName(permissionDo.getName());
            rolePermissionDto.setAdd(rolePermissionHashSet.contains(permissionDo.getId()));
            rolePermissionDtoList.add(rolePermissionDto);
        }
        return rolePermissionDtoList;
    }

    @Override
    protected ResponseDto<List<RolePermissionDto>> generateResult(AuthAccountDo authAccountDo, List<RolePermissionDto> rolePermissionDtoList) {
        ResponseDto responseDataEntity = new ResponseDto();
        responseDataEntity.setResultData(rolePermissionDtoList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
