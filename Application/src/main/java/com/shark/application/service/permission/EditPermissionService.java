package com.shark.application.service.permission;

import com.shark.application.controller.permission.pojo.PermissionEditDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.permission.PermissionRepository;
import com.shark.application.dao.repository.permission.pojo.PermissionDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseResponseService;
import com.shark.application.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditPermissionService extends BaseResponseService<PermissionEditDio> {

    private final PermissionRepository permissionRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, PermissionEditDio permissionEditDio) throws Exception {
        PermissionDo permissionDo = permissionRepository.findById(permissionEditDio.getId())
                .orElseThrow(() -> new WarningException("permission.does.not.exist"));

        if(!StringUtil.isEmpty(permissionEditDio.getName())) {
            permissionDo.setName(permissionEditDio.getName());
        }
        if(!StringUtil.isEmpty(permissionEditDio.getCode())) {
            permissionDo.setCode(permissionEditDio.getCode());
        }
        permissionRepository.save(permissionDo);
        return null;
    }
}
