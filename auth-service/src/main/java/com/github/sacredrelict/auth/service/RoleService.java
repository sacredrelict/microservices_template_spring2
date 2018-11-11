package com.github.sacredrelict.auth.service;

import com.github.sacredrelict.auth.domain.Role;

import java.util.Optional;

/**
 * Service layer interface for Role.
 */
public interface RoleService {

    Optional<Role> getById(Long id);

}
