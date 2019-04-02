package com.shark.application.service.role;

import com.google.common.collect.Lists;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.role.RoleRepository;
import com.shark.application.repository.role.dao.RoleDaoEntity;
import com.shark.application.service.BaseResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EditRoleService extends BaseResponseService {

    public static final String INPUT_ID = "id";
    public static final String INPUT_NAME = "name";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_ID, INPUT_NAME);
    }

    @Override
    protected Void dataAccess(String accountId, HashMap<String, String> parameters) {
        long id = Long.valueOf(parameters.get(INPUT_ID));
        RoleDaoEntity roleDaoEntity = roleRepository.findById(id).get();
        if(roleDaoEntity == null) {
            throw new ResponseException(-1, "Role doesn't exist");
        }
        roleDaoEntity.setName(parameters.get(INPUT_NAME));
        roleRepository.save(roleDaoEntity);
        return null;
    }
}
