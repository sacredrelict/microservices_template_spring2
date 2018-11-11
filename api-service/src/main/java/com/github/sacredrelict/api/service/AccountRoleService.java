package com.github.sacredrelict.api.service;

import com.github.sacredrelict.api.dto.AccountRole;

import java.util.List;

/**
 * Service layer interface for AccountRole.
 */
public interface AccountRoleService {

    List<AccountRole> getRolesByAccountId(Long accountId);

}
