package com.github.sacredrelict.data.service;

import com.github.sacredrelict.data.entity.Role;

import java.util.Optional;

/**
 * Service layer interface for Role.
 */
public interface RoleService {

    Optional<Role> getById(Long id);

}
