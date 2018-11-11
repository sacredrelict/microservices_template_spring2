package com.github.sacredrelict.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableConfigServer - Enable cloud config.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

	/**
	 * Main method to run application.
	 * @param args - Arguments that can be added for application.
     */
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
