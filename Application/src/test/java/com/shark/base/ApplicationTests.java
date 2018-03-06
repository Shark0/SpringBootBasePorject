package com.shark.base;



import com.shark.base.scenario.rest.RestGetScenario;
import com.shark.base.test.listener.TestEventListener;
import com.shark.base.test.worker.ITestWorker;
import com.shark.base.test.worker.MockWorker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class ApplicationTests implements TestEventListener {

	@Autowired
	private MockMvc mockMvc;

	private ITestWorker worker;

	@Before
	public void setUp() throws Exception {
		worker = new MockWorker(mockMvc);
	}


    @Test
    public void test() throws Exception {
		RestGetScenario scenario = new RestGetScenario(this, worker, true);
        scenario.start();
    }

	@Override
	public void success() {
		System.out.println("test success");
		assertTrue(true);
	}

	@Override
	public void error(String errorMessage) {
		System.out.println("test error");
		assertTrue(false);
	}
}
