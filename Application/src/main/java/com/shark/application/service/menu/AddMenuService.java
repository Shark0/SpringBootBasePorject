package com.shark.application.service.menu;

import com.shark.application.controller.menu.pojo.MenuDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.menu.MenuRepository;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import com.shark.application.dto.menu.MenuDto;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddMenuService extends BaseQueryDataService<MenuDio, MenuDo, MenuDto> {

    private final MenuRepository menuRepository;

    @Override
    protected MenuDo process(AuthAccountDo authAccountDo, MenuDio menuDio) throws Exception {
        MenuDo menuDo = new MenuDo();
        menuDo.setName(menuDio.getName());
        if(!StringUtil.isEmpty(menuDio.getIconUrl())) {
            menuDo.setIconUrl(menuDio.getIconUrl());
        }
        if(!StringUtil.isEmpty(menuDio.getPath())) {
            menuDo.setPath(menuDio.getPath());
        }
        if(menuDio.getParentId() == null) {
            menuDo.setParentId(menuDio.getParentId());
        }
        menuDo.setSort(menuDio.getSort());
        menuDo = menuRepository.save(menuDo);
        return menuDo;
    }

    @Override
    protected ResponseDto<MenuDto> generateResult(AuthAccountDo authAccountDo, MenuDo menuDo) {
        ResponseDto<MenuDto> responseDto = new ResponseDto<>();
        responseDto.setResultData(new MenuDto(menuDo));
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
