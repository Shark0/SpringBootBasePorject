package com.shark.application.service.permission;

import com.shark.application.repository.permission.PermissionRepository;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class DeletePermissionService extends BaseResponseService {

    public static final String INPUT_ID = "id";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(INPUT_ID);
        return list;
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) {
        long id = Long.valueOf(parameters.get(INPUT_ID));
        permissionRepository.delete(id);
        return null;
    }
}
