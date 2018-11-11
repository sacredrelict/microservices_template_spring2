package com.github.sacredrelict.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableDiscoveryClient - Mark this service would be discovered by Eureka.
 * @EnableFeignClients - Enable Feign Client for communicate between services.
 * @EnableHystrix - Enable Hystrix framework, that get us ability to intercept exceptions between services.
 * @EnableScheduling - Enable Spring Scheduling mechanism.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableScheduling
public class ApiServiceApplication {

	/**
	 * Main method to run application.
	 * @param args - Arguments that can be added for application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiServiceApplication.class, args);
	}

}
