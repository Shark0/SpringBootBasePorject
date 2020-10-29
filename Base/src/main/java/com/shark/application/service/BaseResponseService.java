package com.shark.application.service;

import com.shark.application.controller.pojo.ResponseDto;
import com.shark.application.controller.pojo.AuthAccountDo;

public abstract class BaseResponseService<Input> extends BaseService<Input, Void, ResponseDto> {


    @Override
    protected ResponseDto<?> generateResult(AuthAccountDo authAccountDo, Void processedData) {
        ResponseDto<?> responseDto = new ResponseDto<>();
        responseDto.setReturnCode(1);
        return responseDto;
    }
}
