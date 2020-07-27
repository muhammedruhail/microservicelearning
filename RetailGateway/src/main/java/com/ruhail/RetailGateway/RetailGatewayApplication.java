package com.ruhail.RetailGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RetailGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailGatewayApplication.class, args);
	}

}
