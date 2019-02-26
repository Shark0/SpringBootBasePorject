package com.shark.application.service;

import com.shark.application.dto.ResponseEntity;
import com.shark.application.exception.ResponseException;
import com.shark.application.util.StringUtil;

import java.util.HashMap;
import java.util.List;

public abstract class BaseStringFromResponseService extends BaseService<HashMap<String, String>, HashMap<String, String>, Void, ResponseEntity> {

    protected abstract List<String> generateCheckKeyList();

    @Override
    protected void checkParameters(HashMap<String, String> parameters) throws Exception {
        List<String> checkKeyList = generateCheckKeyList();
        if(checkKeyList == null || checkKeyList.isEmpty()) {
            return;
        }
        for(String key: checkKeyList) {
            String parameter = parameters.get(key);
            if(StringUtil.isEmpty(parameter)) {
                throw new Exception("Need " + key + " parameter");
            }
        }
    }

    @Override
    protected HashMap<String, String> parseParameters(HashMap<String, String> parameters) {
        return parameters;
    }

    @Override
    protected ResponseEntity generateResultData(Void data) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(1);
        return responseEntity;
    }

    protected ResponseEntity handleResponseException(ResponseException e) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(e.getReturnCode());
        responseEntity.setReturnMessage(e.getMessage());
        return responseEntity;
    }

    @Override
    protected ResponseEntity handleCheckParametersException(Exception e) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(-996);
        responseEntity.setReturnMessage(e.getMessage());
        return responseEntity;
    }

    @Override
    protected ResponseEntity handleParseParametersException(Exception e) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(-997);
        responseEntity.setReturnMessage(e.getMessage());
        return responseEntity;
    }

    @Override
    protected ResponseEntity handleDataAccessException(Exception e) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(-998);
        responseEntity.setReturnMessage(e.getMessage());
        return responseEntity;
    }

    @Override
    protected ResponseEntity handleGenerateResultDataException(Exception e) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(-999);
        responseEntity.setReturnMessage(e.getMessage());
        return responseEntity;
    }
}
