package com.revature.caliber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
public class TrainerRepositoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrainerRepositoryServiceApplication.class, args);
	}
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.revature.caliber.controller"))              
          .paths(PathSelectors.any())                          
          .build();
    }
	
}
