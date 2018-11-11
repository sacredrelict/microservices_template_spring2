package com.github.sacredrelict.auth.config;

import com.github.sacredrelict.auth.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Configuration for security requests for this service.
 * @Configuration - Flag for Spring Boot that this is configuration.
 * @EnableWebSecurity - Enable Spring Boot Web Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Override WebSecurityConfigurerAdapter class method.
     * @return AuthenticationManager object.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    @Bean(name = "authenticationManagerBean")
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }

    /**
     * Configure User Details and password hash.
     * @param auth - AuthenticationManager object.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
