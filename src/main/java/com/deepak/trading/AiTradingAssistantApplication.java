package com.deepak.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AiTradingAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiTradingAssistantApplication.class, args);
	}

}
