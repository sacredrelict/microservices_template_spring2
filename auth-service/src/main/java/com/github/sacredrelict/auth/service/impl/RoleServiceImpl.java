package com.github.sacredrelict.auth.service.impl;

import com.github.sacredrelict.auth.domain.Role;
import com.github.sacredrelict.auth.repositories.RoleRepository;
import com.github.sacredrelict.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer implementation for Role.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Search Role by id.
     * @param id - Role id.
     * @return Role.
     */
    @Override
    public Optional<Role> getById(Long id) {

        return roleRepository.findById(id);
    }

}
