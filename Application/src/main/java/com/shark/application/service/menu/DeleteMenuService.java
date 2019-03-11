package com.shark.application.service.menu;

import com.google.common.collect.Lists;
import com.shark.application.repository.menu.MenuRepository;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DeleteMenuService extends BaseResponseService {

    public static final String INPUT_ID = "id";

    @Autowired
    private MenuRepository menuRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID);
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) {
        long id = Long.valueOf(parameters.get(INPUT_ID));
        menuRepository.delete(id);
        return null;
    }
}
