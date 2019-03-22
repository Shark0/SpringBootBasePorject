package com.shark.application.service.menu;

import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.menu.MenuDtoEntity;
import com.shark.application.repository.menu.MenuRepository;
import com.shark.application.repository.menu.dao.MenuDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetMainMenuListService extends BaseQueryDataService<List<MenuDaoEntity>, List<MenuDaoEntity>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return null;
    }

    @Override
    protected List<MenuDaoEntity> dataAccess(HashMap<String, String> parameters) {
        List<MenuDaoEntity> menuDaoEntityList = menuRepository.findMainMenu();
        return menuDaoEntityList;
    }

    @Override
    protected ResponseDataEntity<List<MenuDaoEntity>> generateResultData(List<MenuDaoEntity> data) {
        ResponseDataEntity<List<MenuDaoEntity>> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
