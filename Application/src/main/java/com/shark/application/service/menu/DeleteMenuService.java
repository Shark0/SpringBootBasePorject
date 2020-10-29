package com.shark.application.service.menu;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.menu.MenuRepository;
import com.shark.application.dao.repository.menu.MenuRoleRepository;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteMenuService extends BaseResponseService<Long> {

    private final MenuRoleRepository menuRoleRepository;
    private final MenuRepository menuRepository;

    @Transactional
    @Override
    protected Void process(AuthAccountDo authAccountDo, Long menuId) throws Exception {
        menuRoleRepository.deleteByMenuId(menuId);
        menuRepository.deleteById(menuId);
        return null;
    }
}
