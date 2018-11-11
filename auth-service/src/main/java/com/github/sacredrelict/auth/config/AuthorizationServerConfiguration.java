package com.github.sacredrelict.auth.config;

import com.github.sacredrelict.auth.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Configuration for OAuth2 authorization service.
 * @Configuration - Flag for Spring Boot that this is configuration.
 * @EnableConfigurationProperties - Enable Spring Boot configuration properties.
 * @EnableAuthorizationServer - Enable Authorization Server for this service.
 */
@Configuration
@EnableConfigurationProperties
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Value("${config.oauth2.publicKey}")
    private String publicKey;

    @Value("${secret.key.api-service}")
    private String secretKeyApiService;

    @Value("${secret.key.api-service-client}")
    private String secretKeyApiServiceClient;

    @Value("${secret.key.data-service-client}")
    private String secretKeyDataServiceClient;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Method for configure security rules for each service authorization.
     * @param configurer - Spring Security configurer.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer.inMemory()
            .withClient("api-service")
                .secret(passwordEncoder.encode(secretKeyApiService))
                .authorizedGrantTypes("password")
                .scopes("api")
                .and()
            .withClient("api-service-client")
                .secret(passwordEncoder.encode(secretKeyApiServiceClient))
                .authorizedGrantTypes("client_credentials")
                .scopes("data", "api")
                .and()
            .withClient("data-service-client")
                .secret(passwordEncoder.encode(secretKeyDataServiceClient))
                .authorizedGrantTypes("client_credentials")
                .scopes("data", "api");
    }

    /**
     * Configure rules for tokens.
     * @param oauthServer - Spring Security AuthorizationServerSecurityConfigurer.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .allowFormAuthenticationForClients();
    }

    /**
     * Configure rules for authentication endpoints.
     * @param endpoints - Spring Security AuthorizationServerEndpointsConfigurer.
     * @throws Exception - Throw exception if something go wrong.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
            .tokenStore(tokenStore())
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .accessTokenConverter(accessTokenConverter());
    }

    /**
     * Bean for token store.
     * @return Token store.
     */
    @Bean
    public TokenStore tokenStore() {

        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * Bean for token converter. It uses keys for secure.
     * @return Token converter.
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

}
