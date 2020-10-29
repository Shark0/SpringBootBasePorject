package com.shark.application.service.account;

import com.shark.application.controller.account.pojo.AccountSearchDio;
import com.shark.application.controller.pojo.AuthAccountDo;
import com.shark.application.controller.pojo.PageDto;
import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.dao.repository.account.AccountRepository;
import com.shark.application.dao.repository.account.pojo.AccountDo;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchAccountListService extends BaseQueryDataService<AccountSearchDio, PageDto<AccountDo>, PageDto<AccountDo>> {

    private final AccountRepository accountRepository;

    @Override
    protected PageDto<AccountDo> process(AuthAccountDo authAccountDo, AccountSearchDio accountSearchDio) throws Exception {
        String keyword = accountSearchDio.getKeyword();
        int pageNumber = accountSearchDio.getPageNumber();
        Pageable pageable = PageRequest.of(pageNumber, 20);
        PageDto<AccountDo> pageDtoEntity = new PageDto<>();
        Page<AccountDo> page;
        if (StringUtil.isEmpty(keyword)) {
            page = accountRepository.findAll(pageable);
        } else {
            page = accountRepository.findByNameContaining(keyword, pageable);
        }
        pageDtoEntity.setContentList(page.getContent());
        pageDtoEntity.setTotalElements(page.getTotalElements());
        pageDtoEntity.setTotalPages(page.getTotalPages());
        return pageDtoEntity;
    }

    @Override
    protected ResponseDto<PageDto<AccountDo>> generateResult(AuthAccountDo authAccountDo, PageDto<AccountDo> accountDoPageDto) {
        ResponseDto<PageDto<AccountDo>> responseDataEntity = new ResponseDto<>();
        responseDataEntity.setResultData(accountDoPageDto);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
