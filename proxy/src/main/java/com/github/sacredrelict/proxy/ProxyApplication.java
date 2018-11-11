package com.github.sacredrelict.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableDiscoveryClient - Mark this service would be discovered by Eureka.
 * @EnableZuulProxy - Enable Zuul proxy.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ProxyApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProxyApplication.class, args);
    }

}
