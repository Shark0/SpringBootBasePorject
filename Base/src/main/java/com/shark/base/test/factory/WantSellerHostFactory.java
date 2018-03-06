package com.shark.base.test.factory;

public class WantSellerHostFactory extends HostFactory {
		
	public String generateHost(Environment enviroment) {
		switch (enviroment) {
		case DEV:
			return "http://127.0.0.1:8080";

		case TEST:
			//FIXME
			return "http://127.0.0.1:8080";
			
		default:
			return "http://127.0.0.1:8080";
		}
		
	}
	
}
