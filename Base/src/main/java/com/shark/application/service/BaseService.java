package com.shark.application.service;

import com.shark.application.exception.DataProcessException;
import com.shark.application.exception.PermissionException;
import com.shark.application.exception.ResultGenerationException;
import com.shark.application.exception.WarningException;
import com.shark.application.controller.pojo.AuthAccountDo;

public abstract class BaseService<Input, ProcessData, Result> {


    public Result request(AuthAccountDo authAccountDo, Input input) throws WarningException, PermissionException, DataProcessException, ResultGenerationException {
        ProcessData processData;
        try {
            processData = process(authAccountDo, input);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WarningException) {
                throw (WarningException) e;
            } else if (e instanceof PermissionException){
                throw (PermissionException) e;
            } else {
                throw new DataProcessException(e);
            }
        }

        Result result = null;
        try {
            result = generateResult(authAccountDo, processData);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof WarningException) {
                throw (WarningException) e;
            } else if (e instanceof PermissionException){
                throw (PermissionException) e;
            } else {
                throw new ResultGenerationException(e);
            }
        }
        return result;
    }

    protected abstract ProcessData process(AuthAccountDo authAccountDo, Input input) throws Exception;

    protected abstract Result generateResult(AuthAccountDo authAccountDo, ProcessData processData);
}
