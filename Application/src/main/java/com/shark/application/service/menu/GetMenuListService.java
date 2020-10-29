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
public class GetMenuListService extends BaseQueryDataService<Void, List<MenuDto>, List<MenuDto>> {

    private final MenuRepository menuRepository;

    @Override
    protected List<MenuDto> process(AuthAccountDo authAccountDo, Void unused) throws Exception {
        List<MenuDo> menuDaoEntityList = menuRepository.findAll();
        List<MenuDto> menuTreeList = generateMenuTree(menuDaoEntityList);
        return menuTreeList;
    }

    private List<MenuDto> generateMenuTree(List<MenuDo> menuDaoEntityList) {
        HashMap<Long, MenuDto> hashMap = new HashMap<>();
        //push menu to hash map
        for(MenuDo menuDo: menuDaoEntityList) {
            hashMap.put(menuDo.getId(), new MenuDto(menuDo));
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
        //add menu to list
        List<MenuDto> menuDtoList = new ArrayList<>();
        for (Long key: hashMap.keySet()) {
            MenuDto menuDto = hashMap.get(key);
            if(menuDto.getParentId() == null || menuDto.getParentId() == 0) {
                menuDtoList.add(menuDto);
            }
        }
        //sort menu list
        menuDtoList.sort((o1, o2) -> {
            if (o1.getSort() < o2.getSort()) {
                return -1;
            }
            if (o1.getSort() > o2.getSort()) {
                return 1;
            }
            return 0;
        });
        return menuDtoList;
    }

    @Override
    protected ResponseDto<List<MenuDto>> generateResult(AuthAccountDo authAccountDo, List<MenuDto> menuDtoList) {
        ResponseDto<List<MenuDto>> responseDto = new ResponseDto<>();
        responseDto.setResultData(menuDtoList);
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
