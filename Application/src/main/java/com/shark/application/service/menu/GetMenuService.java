package com.shark.application.service.menu;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.menu.MenuRepository;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import com.shark.application.dto.menu.MenuDto;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetMenuService extends BaseQueryDataService<Long, MenuDto, MenuDto> {

    private final MenuRepository menuRepository;

    @Override
    protected MenuDto process(AuthAccountDo authAccountDo, Long menuId) throws Exception {
        List<MenuDo> menuDoList = menuRepository.findByMenuIdOrParentId(menuId);
        return generateMenu(menuId, menuDoList);
    }

    private MenuDto generateMenu(long id, List<MenuDo> menuDoList) {
        MenuDto menu = null;
        HashMap<Long, MenuDto> hashMap = new HashMap<>();
        for(MenuDo menuDaoEntity: menuDoList) {
            MenuDto menuDto = new MenuDto(menuDaoEntity);
            hashMap.put(menuDaoEntity.getId(), menuDto);
            if(menuDto.getId() == id) {
                menu = menuDto;
            }
        }
        //add menu to parent
        for (Long key : hashMap.keySet()) {
            MenuDto menuDto = hashMap.get(key);
            if (menuDto.getParentId() != null) {
                MenuDto parent = hashMap.get(menuDto.getParentId());
                if (parent != null) {
                    List<MenuDto> subMenuList = parent.getSubMenuList();
                    if (subMenuList == null) {
                        subMenuList = new ArrayList<>();
                        parent.setSubMenuList(subMenuList);
                    }
                    subMenuList.add(menuDto);
                }
            }
        }
        //sort sub menu
        for (Long key: hashMap.keySet()) {
            MenuDto menuDto = hashMap.get(key);
            List<MenuDto> subMenuList = menuDto.getSubMenuList();
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
    protected ResponseDto<MenuDto> generateResult(AuthAccountDo authAccountDo, MenuDto menuDto) {
        ResponseDto<MenuDto> responseDataEntity = new ResponseDto<>();
        responseDataEntity.setResultData(menuDto);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
