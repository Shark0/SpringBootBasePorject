package com.shark.application.service.account;

import com.shark.application.controller.account.pojo.AccountRoleDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.account.AccountRoleRepository;
import com.shark.application.dao.repository.account.pojo.id.AccountRoleIdDo;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteAccountRoleService extends BaseResponseService<AccountRoleDio> {

    private final AccountRoleRepository accountRoleRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, AccountRoleDio accountRoleDio) throws Exception {
        AccountRoleIdDo accountRoleIdDo = new AccountRoleIdDo();
        accountRoleIdDo.setAccountId(accountRoleDio.getAccountId());
        accountRoleIdDo.setRoleId(accountRoleDio.getRuleId());
        accountRoleRepository.deleteById(accountRoleIdDo);
        return null;
    }
}
