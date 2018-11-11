package com.github.sacredrelict.api.common.component.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Hashing password component.
 */
@Component
public class PasswordEncoder extends BCryptPasswordEncoder {

    private final Integer ENCODING_STRENGTH = 11;

    /**
     * Hash password using BCryptPasswordEncoder with our custom strength.
     * @param password - Password to hash.
     * @return String - password.
     */
    public String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(ENCODING_STRENGTH);
        return passwordEncoder.encode(password);
    }

}
