package com.shark.application.service;

import com.shark.application.controller.pojo.ResponseDto;

public abstract class BaseQueryDataService<Input, ProcessData, Result>
        extends BaseService<Input, ProcessData, ResponseDto<Result>> {
}