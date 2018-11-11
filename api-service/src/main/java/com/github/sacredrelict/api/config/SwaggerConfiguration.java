package com.github.sacredrelict.api.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuration for security resources for this service.
 * @Configuration - Flag for Spring Boot that this is configuration.
 * @EnableSwagger2 - Enable swagger framework.
 * @Profile - Enable this configuration only for some profiles.
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class SwaggerConfiguration {

    /**
     * Bean for init Swagger Docket object.
     * This is base implementation.
     * @return Docket object.
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select().apis(RequestHandlerSelectors.basePackage("com.github.sacredrelict.api.controller"))
            .paths(regex("/api.*"))
            .build()
            .apiInfo(metaData())
            .securityContexts(Lists.newArrayList(securityContext()))
            .securitySchemes(Lists.newArrayList(apiKey()));
    }

    /**
     * Store Swagger description.
     * @return info for Swagger.
     */
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
            "API Service REST API",
            "API Service REST API online documentation",
            "1.0",
            "Terms of service",
            new Contact("Sacredrelict", "http://github.com/", "http://github.com/"),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0");

        return apiInfo;
    }


    /**
     * Secure requests in Swagger.
     * @return instructions for securing.
     */
    private ApiKey apiKey() {
        return new ApiKey("AUTHORIZATION", "Bearer + access token", "header");
    }

    /**
     * Context for security.
     * @return SecurityContext object.
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("/api.*"))
            .build();
    }

    /**
     * Enable default auth.
     * @return list of Security Reference.
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("AUTHORIZATION", authorizationScopes));
    }

}
