package com.shark.base.service;

import com.shark.base.dto.ResponseDataEntity;
import com.shark.base.exception.ResponseException;
import com.shark.base.util.StringUtil;

import java.util.HashMap;
import java.util.List;

public abstract class BaseStringFromResponseDataService<DataAccessObject, DataTransferObject> extends BaseService<HashMap<String, String>, HashMap<String, String>, DataAccessObject, ResponseDataEntity<DataTransferObject>> {

    protected abstract List<String> generateCheckKeyList();


    @Override
    protected void checkParameters(HashMap<String, String> parameters) throws Exception{
        List<String> checkKeyList = generateCheckKeyList();
//        System.out.println("checkParameters checkKeyList size: " + checkKeyList.size());
        if(checkKeyList == null || checkKeyList.isEmpty()) {
            return;
        }
        for(String key: checkKeyList) {
            String parameter = parameters.get(key);
//            System.out.println("key: " + key + " , value: " + parameter);
            if(StringUtil.isEmpty(parameter)) {
                throw new Exception("Need " + key + " parameter");
            }
        }
    }

    @Override
    protected HashMap<String, String> parseParameters(HashMap<String, String> parameters) {
        return parameters;
    }

    protected ResponseDataEntity<DataTransferObject> handleResponseException(ResponseException e) {
        ResponseDataEntity<DataTransferObject> responseDataEntity = new ResponseDataEntity<DataTransferObject>();
        responseDataEntity.setReturnCode(e.getReturnCode());
        responseDataEntity.setReturnMessage(e.getReturnMessage());
        return responseDataEntity;
    }

    @Override
    protected ResponseDataEntity<DataTransferObject> handleCheckParametersException(Exception e) {
        ResponseDataEntity<DataTransferObject> responseDataEntity = new ResponseDataEntity<DataTransferObject>();
        responseDataEntity.setReturnCode(-996);
        responseDataEntity.setReturnMessage(e.getMessage());
        return responseDataEntity;
    }

    @Override
    protected ResponseDataEntity<DataTransferObject> handleParseParametersException(Exception e) {
        ResponseDataEntity<DataTransferObject> responseDataEntity = new ResponseDataEntity<DataTransferObject>();
        responseDataEntity.setReturnCode(-997);
        responseDataEntity.setReturnMessage(e.getMessage());
        return responseDataEntity;
    }

    @Override
    protected ResponseDataEntity<DataTransferObject> handleDataAccessException(Exception e) {
        ResponseDataEntity<DataTransferObject> responseDataEntity = new ResponseDataEntity<DataTransferObject>();
        responseDataEntity.setReturnCode(-998);
        responseDataEntity.setReturnMessage(e.getMessage());
        return responseDataEntity;
    }

    @Override
    protected ResponseDataEntity<DataTransferObject> handleGenerateResultDataException(Exception e) {
        ResponseDataEntity<DataTransferObject> responseDataEntity = new ResponseDataEntity<DataTransferObject>();
        responseDataEntity.setReturnCode(-999);
        responseDataEntity.setReturnMessage(e.getMessage());
        return responseDataEntity;
    }
}
