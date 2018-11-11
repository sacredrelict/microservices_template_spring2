package com.github.sacredrelict.api.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        Collection<GrantedAuthority> adminRoles = new ArrayList<>();
        adminRoles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        adminRoles.add(new SimpleGrantedAuthority("ROLE_USER"));
        User adminUser = new User("admin", "admin@github.com", adminRoles);

        Collection<GrantedAuthority> testRoles = new ArrayList<>();
        adminRoles.add(new SimpleGrantedAuthority("ROLE_USER"));
        User testUser = new User("test", "test@github.com", testRoles);

        return new InMemoryUserDetailsManager(Arrays.asList(adminUser, testUser));
    }

}
