package com.github.sacredrelict.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableDiscoveryClient - Mark this service would be discovered by Eureka.
 * @EnableHystrix - Enable Hystrix framework, that get us ability to intercept exceptions between services.
 * @EnableGlobalMethodSecurity - Enable global security for requests.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServiceApplication {

	/**
	 * Main method to run application.
	 * @param args - Arguments that can be added for application.
	 */
	public static void main(String[] args) {

		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
