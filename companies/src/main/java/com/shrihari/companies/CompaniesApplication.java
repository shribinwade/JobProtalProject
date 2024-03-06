package com.shrihari.companies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CompaniesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniesApplication.class, args);
	}

}
