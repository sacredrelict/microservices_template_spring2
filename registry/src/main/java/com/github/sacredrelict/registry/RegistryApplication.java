package com.github.sacredrelict.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableEurekaServer - Enable Eureka server.
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

	/**
	 * Main method to run application.
	 * @param args - Arguments that can be added for application.
	 */
	public static void main(String[] args) {

		SpringApplication.run(RegistryApplication.class, args);
	}

}
