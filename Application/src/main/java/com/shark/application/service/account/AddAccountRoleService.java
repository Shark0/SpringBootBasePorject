package com.shark.application.service.account;

import com.shark.application.controller.account.pojo.AccountRoleDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.account.AccountRoleRepository;
import com.shark.application.dao.repository.account.pojo.AccountRoleDo;
import com.shark.application.service.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddAccountRoleService extends BaseResponseService<AccountRoleDio> {

    private final AccountRoleRepository accountRoleRepository;

    @Override
    protected Void process(AuthAccountDo authAccountDo, AccountRoleDio accountRoleDio) throws Exception {
        AccountRoleDo accountRoleDo = new AccountRoleDo();
        accountRoleDo.setAccountId(accountRoleDio.getAccountId());
        accountRoleDo.setRoleId(accountRoleDio.getRuleId());
        accountRoleRepository.save(accountRoleDo);
        return null;
    }
}
