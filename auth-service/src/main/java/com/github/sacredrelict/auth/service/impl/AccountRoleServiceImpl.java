package com.github.sacredrelict.auth.service.impl;

import com.github.sacredrelict.auth.domain.AccountRole;
import com.github.sacredrelict.auth.repositories.AccountRoleRepository;
import com.github.sacredrelict.auth.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer implementation for AccountRole.
 */
@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public Optional<AccountRole> getById(Long id) {

        return accountRoleRepository.findById(id);
    }

    /**
     * Search all account roles by account id.
     * @param accountId - user id.
     * @return - list of Account Roles.
     */
    @Override
    public List<AccountRole> getRolesByAccountId(Long accountId) {

        return accountRoleRepository.findByAccountId(accountId);
    }

}
