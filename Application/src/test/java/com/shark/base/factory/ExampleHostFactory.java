package com.shark.base.factory;

import com.shark.base.test.factory.HostFactory;

public class ExampleHostFactory extends HostFactory {

	@Override
	public String generateHost(HostFactory.Environment environment) {
		switch (environment) {
			case DEV:
				return "http://127.0.0.1:8080";

			case TEST:
				//FIXME
				return "http://127.0.0.1:8080";

			default:
				//FIXME
				return "http://127.0.0.1:8080";
		}
	}
}
