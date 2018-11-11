package com.github.sacredrelict.auth.repositories;

import com.github.sacredrelict.auth.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Role entity.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

}
