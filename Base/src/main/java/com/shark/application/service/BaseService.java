package com.shark.application.service;

import com.shark.application.exception.ResponseException;

public abstract class BaseService<Parameters, DataAccessParameters, DataAccessObject, Result> {


    public Result request(String accountId, Parameters parameters) {
        try {
            checkParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ResponseException) {
                return handleResponseException((ResponseException) e);
            }
            return handleCheckParametersException(e);
        }

        DataAccessParameters dataAccessParameters = null;
        try {
            dataAccessParameters = parseParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ResponseException) {
                return handleResponseException((ResponseException) e);
            }
            return handleParseParametersException(e);
        }

        DataAccessObject dataAccessObject = null;
        try {
            dataAccessObject = dataAccess(accountId, dataAccessParameters);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ResponseException) {
                return handleResponseException((ResponseException) e);
            }
            return handleDataAccessException(e);
        }

        Result result = null;
        try {
            result = generateResultData(accountId, dataAccessObject);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ResponseException) {
                return handleResponseException((ResponseException) e);
            }
            return handleGenerateResultDataException(e);
        }
        return result;
    }

    protected abstract void checkParameters(Parameters parameters) throws Exception;

    protected abstract DataAccessParameters parseParameters(Parameters parameters);

    protected abstract DataAccessObject dataAccess(String accountId, DataAccessParameters parameters) throws Exception;

    protected abstract Result generateResultData(String accountId, DataAccessObject dataAccessObject);

    protected abstract Result handleResponseException(ResponseException exception);

    protected abstract Result handleCheckParametersException(Exception e);

    protected abstract Result handleParseParametersException(Exception e);

    protected abstract Result handleDataAccessException(Exception e);

    protected abstract Result handleGenerateResultDataException(Exception e);
}
