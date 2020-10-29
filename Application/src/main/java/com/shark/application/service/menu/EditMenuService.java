package com.shark.application.service.menu;

import com.shark.application.controller.menu.pojo.MenuEditDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.menu.MenuRepository;
import com.shark.application.dao.repository.menu.pojo.MenuDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseResponseService;
import com.shark.application.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditMenuService extends BaseResponseService<MenuEditDio> {

    private final MenuRepository menuRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, MenuEditDio menuEditDio) throws Exception {
        MenuDo menuDo = menuRepository.findById(menuEditDio.getId()).orElseThrow(() -> new WarningException("menu.does.not.exist"));
        if (!StringUtil.isEmpty(menuEditDio.getName())) {
            menuDo.setName(menuEditDio.getName());
        }
        if (!StringUtil.isEmpty(menuEditDio.getIconUrl())) {
            menuDo.setIconUrl(menuEditDio.getIconUrl());
        }
        if (!StringUtil.isEmpty(menuEditDio.getPath())) {
            menuDo.setPath(menuEditDio.getPath());
        }
        if (menuEditDio.getParentId() != null) {
            menuDo.setParentId(menuEditDio.getParentId());
        }
        if (menuEditDio.getSort() != null) {
            menuDo.setSort(menuEditDio.getSort());
        }
        menuRepository.save(menuDo);
        return null;
    }
}
