package com.shark.application.service.menu;

import com.google.common.collect.Lists;
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
public class GetMenuService extends BaseQueryDataService<MenuDtoEntity, MenuDtoEntity> {

    public static final String INPUT_ID = "id";

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected MenuDtoEntity dataAccess(HashMap<String, String> parameters) {
        //find menu list
        String id = parameters.get(INPUT_ID);
        List<MenuDaoEntity> menuDaoEntityList = menuRepository.findById(Long.valueOf(id));
        return generateMenu(Long.valueOf(id), menuDaoEntityList);
    }

    private MenuDtoEntity generateMenu(long id, List<MenuDaoEntity> menuDaoEntityList) {
        MenuDtoEntity menu = null;
        HashMap<Long, MenuDtoEntity> hashMap = new HashMap<>();
        //push menu to hash map
        for(MenuDaoEntity menuDaoEntity: menuDaoEntityList) {
            MenuDtoEntity menuDtoEntity = new MenuDtoEntity(menuDaoEntity);
            hashMap.put(menuDaoEntity.getId(), menuDtoEntity);
            if(menuDtoEntity.getId() == id) {
                menu = menuDtoEntity;
            }
        }
        //add menu to parent
        for (Long key : hashMap.keySet()) {
            MenuDtoEntity menuDtoEntity = hashMap.get(key);
            if (menuDtoEntity.getParentId() != null) {
                MenuDtoEntity parent = hashMap.get(menuDtoEntity.getParentId());
                if (parent != null) {
                    List<MenuDtoEntity> subMenuList = parent.getSubMenuList();
                    if (subMenuList == null) {
                        subMenuList = new ArrayList<>();
                        parent.setSubMenuList(subMenuList);
                    }
                    subMenuList.add(menuDtoEntity);
                }
            }
        }
        //sort sub menu
        for (Long key: hashMap.keySet()) {
            MenuDtoEntity menuDtoEntity = hashMap.get(key);
            List<MenuDtoEntity> subMenuList = menuDtoEntity.getSubMenuList();
            if(subMenuList != null) {
                subMenuList.sort((o1, o2) -> {
                    if (o1.getSort() < o2.getSort()) {
                        return -1;
                    }
                    if (o1.getSort() > o2.getSort()) {
                        return 1;
                    }
                    return 0;
                });
            }
        }
        return menu;
    }

    @Override
    protected ResponseDataEntity<MenuDtoEntity> generateResultData(MenuDtoEntity data) {
        ResponseDataEntity<MenuDtoEntity> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
