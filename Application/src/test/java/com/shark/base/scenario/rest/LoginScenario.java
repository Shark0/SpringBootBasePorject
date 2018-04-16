package com.shark.base.scenario.rest;

import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.scenario.BaseExampleScenario;
import com.shark.base.test.listener.TestEventListener;
import com.shark.base.test.worker.ITestWorker;
import com.shark.base.test.worker.ResponseResultEntity;

import java.util.HashMap;

public class LoginScenario extends BaseExampleScenario {


    public LoginScenario(TestEventListener listener, ITestWorker worker, boolean isDebug) {
        super(listener, worker, isDebug);
    }

    @Override
    protected void scenario() throws Exception {
        String apiPath = "/login";
        HashMap<String, String> headerHashMap = new HashMap();
        headerHashMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerHashMap.put("charset", "utf-8");
        HashMap<String, String> bodyHashMap = new HashMap<>();
        bodyHashMap.put(MemberDaoEntity.ACCOUNT, "Shark@gmail.com");
        bodyHashMap.put(MemberDaoEntity.PASSWORD,  "Shark");
        ResponseResultEntity responseResultEntity = worker.post(host, apiPath, headerHashMap, bodyHashMap, isDebug);
        checkStatusOk(responseResultEntity.getContent());
    }
}
