package com.github.sacredrelict.data.buider;

import com.github.sacredrelict.data.entity.Role;

/**
 * Builder for Role.
 */
public class RoleBuilder {

    public Role createRole() {
        Role role = new Role();
        role.setId(11L);
        role.setName("test role");
        return role;
    }

}
