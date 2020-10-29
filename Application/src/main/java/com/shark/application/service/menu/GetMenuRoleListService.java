package com.shark.application.service.menu;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.role.RoleRepository;
import com.shark.application.dao.repository.role.pojo.RoleDo;
import com.shark.application.dto.menu.MenuRoleDto;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetMenuRoleListService extends BaseQueryDataService<Long, List<MenuRoleDto>, List<MenuRoleDto>> {

    private final RoleRepository roleRepository;

    @Override
    protected List<MenuRoleDto> process(AuthAccountDo authAccountDo, Long menuId) throws Exception {
        List<RoleDo> roleList = roleRepository.findByMenuId(menuId);
        HashMap<Long, RoleDo> menuRoleHashMap = new HashMap<>();
        for(RoleDo roleDo: roleList) {
            menuRoleHashMap.put(roleDo.getId(), roleDo);
        }

        List<RoleDo> roleDoList = roleRepository.findAll();
        List<MenuRoleDto> menuRoleDtoList = new ArrayList<>();
        for(RoleDo roleDo: roleDoList) {
            MenuRoleDto menuRoleDto = new MenuRoleDto();
            menuRoleDto.setRoleId(roleDo.getId());
            menuRoleDto.setRoleName(roleDo.getName());
            menuRoleDto.setAdd(menuRoleHashMap.get(roleDo.getId()) != null);
            menuRoleDtoList.add(menuRoleDto);
        }
        return menuRoleDtoList;
    }

    @Override
    protected ResponseDto<List<MenuRoleDto>> generateResult(AuthAccountDo authAccountDo, List<MenuRoleDto> menuRoleDtoList) {
        ResponseDto<List<MenuRoleDto>> responseDataEntity = new ResponseDto<>();
        responseDataEntity.setResultData(menuRoleDtoList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
