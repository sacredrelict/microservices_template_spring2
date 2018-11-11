package com.github.sacredrelict.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * Spring Boot main runner class.
 * @SpringBootApplication - Flag for Spring Boot application.
 * @EnableDiscoveryClient - Mark this service would be discovered by Eureka.
 * @EnableFeignClients - Enable Feign Client for communicate between services.
 * @EnableConfigurationProperties - Enable Spring Boot configuration properties.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
public class DataServiceApplication {

	/**
	 * Main method to run application.
	 * @param args - Arguments that can be added for application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DataServiceApplication.class, args);
	}

	/**
	 * Bean for base initialization of Hibernate ObjectMapper.
	 * @return ObjectMapper object.
     */
	@Bean
	public ObjectMapper configureObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();

		mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

		//It is necessary in order to lazy-loaded object should be serialized as map IdentifierName=>IdentifierValue.
		Hibernate5Module module = new Hibernate5Module();
		module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
		mapper.registerModule(module);

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		mapper.registerModule(javaTimeModule);

		return mapper;
	}

	@Bean
	protected Module module() {
		return new Hibernate5Module();
	}


}
