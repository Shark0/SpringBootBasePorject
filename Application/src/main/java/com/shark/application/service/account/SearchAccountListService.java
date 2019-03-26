package com.shark.application.service.account;

import com.google.common.collect.Lists;
import com.shark.application.dto.PageDtoEntity;
import com.shark.application.dto.ResponseDataEntity;
import com.shark.application.exception.ResponseException;
import com.shark.application.repository.account.AccountRepository;
import com.shark.application.repository.account.dao.AccountDaoEntity;
import com.shark.application.service.BaseQueryDataService;
import com.shark.application.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SearchAccountListService extends BaseQueryDataService<PageDtoEntity<AccountDaoEntity>, PageDtoEntity<AccountDaoEntity>> {

    public static final String INPUT_PAGE_NUMBER = "pageNumber";

    public static final String INPUT_PAGE_SIZE = "pageSize";

    public static final String INPUT_KEYWORD = "keyword";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        return Lists.newArrayList(INPUT_PAGE_NUMBER, INPUT_PAGE_SIZE);
    }

    @Override
    protected PageDtoEntity<AccountDaoEntity> dataAccess(HashMap<String, String> parameters) throws Exception {
        String pageNumber = parameters.get(INPUT_PAGE_NUMBER);
        String pageSize = parameters.get(INPUT_PAGE_SIZE);
        if(StringUtil.isEmpty(pageSize) || "0".equalsIgnoreCase(pageSize)) {
            throw new ResponseException(-1, "頁面大小不能為0");
        }
        String keyword = parameters.get(INPUT_KEYWORD);
        int pageNumberInt = Integer.valueOf(pageNumber);
        int pageSizeInt = Integer.valueOf(pageSize);
        Pageable pageable = new PageRequest(pageNumberInt, pageSizeInt);
        PageDtoEntity<AccountDaoEntity> pageDtoEntity = new PageDtoEntity<>();
        Page<AccountDaoEntity> page;
        if(StringUtil.isEmpty(keyword)) {
            page = accountRepository.findAll(pageable);
        } else {
            page = accountRepository.findByNameContaining(keyword, pageable);
        }
        pageDtoEntity.setContent(page.getContent());
        pageDtoEntity.setTotalElements(page.getTotalElements());
        pageDtoEntity.setTotalPages(page.getTotalPages());
        return pageDtoEntity;
    }

    @Override
    protected ResponseDataEntity<PageDtoEntity<AccountDaoEntity>> generateResultData(PageDtoEntity<AccountDaoEntity> data) {
        ResponseDataEntity<PageDtoEntity<AccountDaoEntity>> responseDataEntity = new ResponseDataEntity<>();
        responseDataEntity.setData(data);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
