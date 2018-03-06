package com.shark.base.scenario;


import com.shark.base.factory.ExampleHostFactory;
import com.shark.base.test.factory.HostFactory;
import com.shark.base.test.listener.TestEventListener;
import com.shark.base.test.scenario.BaseTestScenario;
import com.shark.base.test.worker.ITestWorker;

public abstract class BaseExampleScenario extends BaseTestScenario {

     public BaseExampleScenario(TestEventListener listener, ITestWorker worker, boolean isDebug) {
        super(listener, worker, isDebug);
    }

    @Override
    protected String generateHost() {
        return new ExampleHostFactory().generateHost(HostFactory.Environment.DEV);
    }
}
