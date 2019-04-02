package com.shark.application.service.menu;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.menu.MenuDtoEntity;
import com.shark.application.repository.menu.MenuRepository;
import com.shark.application.repository.menu.dao.MenuDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AddMenuService extends BaseQueryDataService<MenuDaoEntity, MenuDtoEntity> {

    public static final String INPUT_NAME = "name";

    public static final String INPUT_ICON_URL = "iconUrl";

    public static final String INPUT_PATH = "path";

    public static final String INPUT_PARENT_ID = "parentId";

    public static final String INPUT_SORT = "sort";

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_NAME, INPUT_SORT);
    }

    @Override
    protected MenuDaoEntity dataAccess(String accountId, HashMap<String, String> parameters) {
        String name = parameters.get(INPUT_NAME);
        String iconUrl = parameters.get(INPUT_ICON_URL);
        String path = parameters.get(INPUT_PATH);
        String parentId = parameters.get(INPUT_PARENT_ID);
        String sort = parameters.get(INPUT_SORT);
        MenuDaoEntity menuDaoEntity = new MenuDaoEntity();
        menuDaoEntity.setName(name);
        if(!StringUtil.isEmpty(iconUrl)) {
            menuDaoEntity.setIconUrl(iconUrl);
        }
        if(!StringUtil.isEmpty(path)) {
            menuDaoEntity.setPath(path);
        }
        if(!StringUtil.isEmpty(parentId)) {
            menuDaoEntity.setParentId(Long.valueOf(parentId));
        }
        menuDaoEntity.setSort(Integer.valueOf(sort));
        menuDaoEntity = menuRepository.save(menuDaoEntity);
        return menuDaoEntity;
    }

    @Override
    protected ResponseDataEntity<MenuDtoEntity> generateResultData(String accountId, MenuDaoEntity menuDaoEntity) {
        ResponseDataEntity<MenuDtoEntity> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(new MenuDtoEntity(menuDaoEntity));
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
