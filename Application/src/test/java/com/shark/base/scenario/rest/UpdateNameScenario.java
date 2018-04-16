package com.shark.base.scenario.rest;

import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.scenario.BaseExampleScenario;
import com.shark.base.security.Config;
import com.shark.base.test.listener.TestEventListener;
import com.shark.base.test.worker.ITestWorker;
import com.shark.base.test.worker.ResponseResultEntity;

import java.util.HashMap;

public class UpdateNameScenario extends BaseExampleScenario {

    private String authHeaderParameter;

    public UpdateNameScenario(TestEventListener listener, ITestWorker worker, boolean isDebug, String authHeaderParameter) {
        super(listener, worker, isDebug);
        this.authHeaderParameter = authHeaderParameter;
    }

    @Override
    protected void scenario() throws Exception {
        String apiPath = "/member/updateName";
        HashMap<String, String> headerHashMap = new HashMap();
        headerHashMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerHashMap.put("charset", "utf-8");
        headerHashMap.put(Config.HEADER, authHeaderParameter);

        HashMap<String, String> bodyHashMap = new HashMap<>();
        bodyHashMap.put(MemberDaoEntity.NAME, "CrazyShark");
        ResponseResultEntity responseResultEntity = worker.post(host, apiPath, headerHashMap, bodyHashMap, isDebug);
        checkStatusOk(responseResultEntity.getContent());
    }
}
