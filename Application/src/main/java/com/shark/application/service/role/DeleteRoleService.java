package com.shark.application.service.role;

import com.shark.application.repository.role.RoleRepository;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class DeleteRoleService extends BaseResponseService {

    public static final String INPUT_ID = "id";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(INPUT_ID);
        return list;
    }

    @Override
    protected Void dataAccess(String accountId, HashMap<String, String> parameters) {
        long id = Long.valueOf(parameters.get(INPUT_ID));
        roleRepository.deleteById(id);
        return null;
    }
}
