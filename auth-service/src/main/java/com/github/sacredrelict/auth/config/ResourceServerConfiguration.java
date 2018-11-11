package com.github.sacredrelict.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Configuration for security resources for this service.
 * @Configuration - Flag for Spring Boot that this is configuration.
 * @EnableResourceServer - Enable Spring Boot resource server.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * Configure requests that should be open or secured.
     * @param http - HttpSecurity object.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/users/**", "/uaa/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

}
