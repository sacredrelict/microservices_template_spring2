package com.github.sacredrelict.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableDiscoveryClient - Mark this service would be discovered by Eureka.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);
    }

}
