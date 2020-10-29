package com.shark.application.service.account;

import com.shark.application.controller.account.pojo.AccountDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseResponseService;
import com.shark.application.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditAccountService extends BaseResponseService<AccountDio> {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected Void process(AuthAccountDo authAccountDo, AccountDio accountDio) throws Exception {
        AccountDo accountDo = accountRepository.findById(authAccountDo.getId())
                .orElseThrow(() -> new WarningException("account.does.not.exist"));
        if (!StringUtil.isEmpty(accountDio.getName())) {
            accountDo.setName(accountDio.getName());
        }
        if (!StringUtil.isEmpty(accountDio.getPassword())) {
            String encryptedPwd = bCryptPasswordEncoder.encode(accountDio.getPassword());
            accountDo.setPassword(encryptedPwd);
        }
        accountRepository.save(accountDo);
        return null;
    }
}
