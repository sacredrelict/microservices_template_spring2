package com.github.sacredrelict.api.config;

import com.github.sacredrelict.api.common.component.security.CustomUserInfoTokenServices;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * Configuration for security resources for this service.
 * @Configuration - Flag for Spring Boot that this is configuration.
 * @EnableResourceServer - Enable Spring Boot resource server.
 * @EnableOAuth2Client - Mark this service would be client of OAuth service.
 * @EnableConfigurationProperties - Enable Spring Boot configuration properties.
 * @EnableGlobalMethodSecurity - Enable global security for this service.
 */
@Configuration
@EnableResourceServer
@EnableOAuth2Client
@EnableConfigurationProperties
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerProperties sso;

    /**
     * Configure requests that should be open or secured.
     * @param http - HttpSecurity object.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/index").permitAll()
            .antMatchers(HttpMethod.GET, "/v2/**").permitAll()
            .antMatchers(HttpMethod.GET, "/configuration/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
            .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable();
    }

    /**
     * Bean for client credentials details.
     * @return ClientCredentialsResourceDetails object.
     */
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    /**
     * Bean for request interceptor.
     * @return RequestInterceptor object.
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    }

    /**
     * Bean for client credentials template.
     * @return OAuth2RestTemplate object.
     */
    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }

    /**
     * Bean for resource token.
     * @return OAuth2RestTemplate object.
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }

}
