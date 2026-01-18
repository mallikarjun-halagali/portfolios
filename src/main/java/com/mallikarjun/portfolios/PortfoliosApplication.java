package com.mallikarjun.portfolios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortfoliosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfoliosApplication.class, args);
	}

}
