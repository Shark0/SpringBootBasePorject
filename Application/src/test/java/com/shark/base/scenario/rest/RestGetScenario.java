package com.shark.base.scenario.rest;

import com.shark.base.dto.ResponseEntity;
import com.shark.base.scenario.BaseExampleScenario;
import com.shark.base.test.listener.TestEventListener;
import com.shark.base.test.worker.ITestWorker;
import com.shark.base.test.worker.ResponseResultEntity;

import java.util.HashMap;

public class RestGetScenario extends BaseExampleScenario {


    public RestGetScenario(TestEventListener listener, ITestWorker worker, boolean isDebug) {
        super(listener, worker, isDebug);
    }

    @Override
    protected void scenario() throws Exception {
        String apiPath = "/rest/get";
        HashMap<String, String> headerHashMap = new HashMap();
        headerHashMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerHashMap.put("charset", "utf-8");
        ResponseResultEntity responseResultEntity = worker.get(host, apiPath, headerHashMap, "", isDebug);
        checkStatusOk(responseResultEntity.getContent());
    }
}
