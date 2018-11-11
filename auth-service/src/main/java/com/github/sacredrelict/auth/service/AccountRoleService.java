package com.github.sacredrelict.auth.service;

import com.github.sacredrelict.auth.domain.AccountRole;

import java.util.List;
import java.util.Optional;

/**
 * Service layer interface for AccountRole.
 */
public interface AccountRoleService {

    Optional<AccountRole> getById(Long id);

    List<AccountRole> getRolesByAccountId(Long accountId);

}
