package com.shark.application.service.menu;

import com.google.common.collect.Lists;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.menu.MenuRepository;
import com.shark.application.repository.menu.dao.MenuDaoEntity;
import com.shark.application.service.BaseResponseService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EditMenuService extends BaseResponseService {

    public static final String INPUT_ID = "id";

    public static final String INPUT_NAME = "name";

    public static final String INPUT_ICON_URL = "iconUrl";

    public static final String INPUT_PATH = "path";

    public static final String INPUT_PARENT_ID = "parentId";

    public static final String INPUT_SORT = "sort";

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) {
        String id = parameters.get(INPUT_ID);
        String name = parameters.get(INPUT_NAME);
        String iconUrl = parameters.get(INPUT_ICON_URL);
        String path = parameters.get(INPUT_PATH);
        String parentId = parameters.get(INPUT_PARENT_ID);
        String sort = parameters.get(INPUT_SORT);
        MenuDaoEntity menuDaoEntity = menuRepository.findOne(Long.valueOf(id));
        if(menuDaoEntity == null) {
            throw new ResponseException(-1, "Menu is not exist");
        }
        if(!StringUtil.isEmpty(name)) {
            menuDaoEntity.setName(name);
        }
        if(!StringUtil.isEmpty(iconUrl)) {
            menuDaoEntity.setIconUrl(iconUrl);
        }
        if(!StringUtil.isEmpty(path)) {
            menuDaoEntity.setPath(path);
        }
        if(!StringUtil.isEmpty(parentId)) {
            menuDaoEntity.setParentId(Long.valueOf(parentId));
        }
        if(!StringUtil.isEmpty(sort)) {
            menuDaoEntity.setSort(Double.valueOf(sort));
        }
        menuRepository.save(menuDaoEntity);
        return null;
    }

}
