package com.revature.hydra.trainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
public class TrainerRepositoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrainerRepositoryServiceApplication.class, args);
	}
	
}
