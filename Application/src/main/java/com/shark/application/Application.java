package com.shark.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean("encoder")
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@GetMapping("/")
	public String home() {
		return "Welcome!";
	}
}
