package com.shark.base.helper;

import com.shark.base.factory.ExampleHostFactory;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.security.Config;
import com.shark.base.test.factory.HostFactory;
import com.shark.base.test.worker.ITestWorker;
import com.shark.base.test.worker.ResponseResultEntity;

import java.util.HashMap;

public class LoginHelper {

    protected String host;
    protected ITestWorker worker;
    protected boolean isDebug;

    public LoginHelper(ITestWorker worker) {
        this.worker = worker;
        isDebug = true;
        host = new ExampleHostFactory().generateHost(HostFactory.Environment.DEV);
    }

    public String login() throws Exception {
        String apiPath = "/login";
        HashMap<String, String> headerHashMap = new HashMap();
        headerHashMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerHashMap.put("charset", "utf-8");

        HashMap<String, String> bodyHashMap = new HashMap<>();
        bodyHashMap.put(MemberDaoEntity.ACCOUNT, "Shark@gmail.com");
        bodyHashMap.put(MemberDaoEntity.PASSWORD,  "Shark");
        ResponseResultEntity responseResultEntity = worker.post(host, apiPath, headerHashMap, bodyHashMap, isDebug);

        return responseResultEntity.getHeaders().get(Config.HEADER).get(0);
    }
}
