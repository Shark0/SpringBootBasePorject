package com.shark.application.service.menu;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.menu.MenuRepository;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetMainMenuListService extends BaseQueryDataService<Void, List<MenuDo>, List<MenuDo>> {

    private final MenuRepository menuRepository;

    @Override
    protected List<MenuDo> process(AuthAccountDo authAccountDo, Void unused) throws Exception {
        return menuRepository.findMainMenu();
    }

    @Override
    protected ResponseDto<List<MenuDo>> generateResult(AuthAccountDo authAccountDo, List<MenuDo> menuDoList) {
        ResponseDto<List<MenuDo>> responseDataEntity = new ResponseDto<>();
        responseDataEntity.setResultData(menuDoList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
