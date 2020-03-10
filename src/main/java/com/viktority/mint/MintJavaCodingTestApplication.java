package com.viktority.mint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MintJavaCodingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MintJavaCodingTestApplication.class, args);
		System.err.println("System up!");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new  RestTemplate();
	}
}
