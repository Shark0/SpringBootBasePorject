package com.shark.application.service.account;

import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.exception.WarningException;
import com.shark.application.service.BaseQueryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAccountService extends BaseQueryDataService<Long, AccountDo, AccountDo> {

    private final AccountRepository accountRepository;

    @Override
    protected AccountDo process(AuthAccountDo authAccountDo, Long accountId) throws Exception {
        return accountRepository.findById(accountId).orElseThrow(() -> new WarningException("account.does.not.exist"));
    }

    @Override
    protected ResponseDto<AccountDo> generateResult(AuthAccountDo authAccountDo, AccountDo accountDo) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultData(accountDo);
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
