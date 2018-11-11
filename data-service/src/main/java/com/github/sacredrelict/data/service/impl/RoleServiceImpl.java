package com.github.sacredrelict.data.service.impl;

import com.github.sacredrelict.data.entity.Role;
import com.github.sacredrelict.data.repository.RoleRepository;
import com.github.sacredrelict.data.service.RoleService;
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
     * Get Role by id.
     * @param id - Role id.
     * @return - Role object.
     */
    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

}
