package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.dto.menu.MenuDtoEntity;
import com.shark.application.repository.menu.MenuRepository;
import com.shark.application.repository.menu.dao.MenuDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetAccountMenuListService extends BaseQueryDataService<List<MenuDtoEntity>, List<MenuDtoEntity>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return null;
    }

    @Override
    protected List<MenuDtoEntity> dataAccess(String accountId, HashMap<String, String> parameters) {
        //find menu list
        List<MenuDaoEntity> menuDaoEntityList = menuRepository.findByAccountId(accountId);
        List<MenuDtoEntity> menuTreeList = generateMenuTree(menuDaoEntityList);
        return menuTreeList;
    }

    private List<MenuDtoEntity> generateMenuTree(List<MenuDaoEntity> menuDaoEntityList) {
        HashMap<Long, MenuDtoEntity> hashMap = new HashMap<>();
        //push menu to hash map
        for(MenuDaoEntity menuDaoEntity: menuDaoEntityList) {
            hashMap.put(menuDaoEntity.getId(), new MenuDtoEntity(menuDaoEntity));
        }
        //add menu to parent
        for (Long key : hashMap.keySet()) {
            MenuDtoEntity menuDtoEntity = hashMap.get(key);
            if (menuDtoEntity.getParentId() != null && menuDtoEntity.getParentId() != 0) {
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
        //add menu to list
        List<MenuDtoEntity> menuDtoEntityList = new ArrayList<>();
        for (Long key: hashMap.keySet()) {
            MenuDtoEntity menuDtoEntity = hashMap.get(key);
            if(menuDtoEntity.getParentId() == null || menuDtoEntity.getParentId() == 0) {
                //main menu
                if(!StringUtil.isEmpty(menuDtoEntity.getPath()) ||
                        (menuDtoEntity.getSubMenuList() != null && menuDtoEntity.getSubMenuList().size() > 0)) {
                    //has path or children
                    menuDtoEntityList.add(menuDtoEntity);
                }
            }
        }
        //sort menu list
        menuDtoEntityList.sort((o1, o2) -> {
            if (o1.getSort() < o2.getSort()) {
                return -1;
            }
            if (o1.getSort() > o2.getSort()) {
                return 1;
            }
            return 0;
        });
        return menuDtoEntityList;
    }

    @Override
    protected ResponseDataEntity<List<MenuDtoEntity>> generateResultData(String accountId, List<MenuDtoEntity> menuDtoEntityList) {
        ResponseDataEntity<List<MenuDtoEntity>> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(menuDtoEntityList);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
