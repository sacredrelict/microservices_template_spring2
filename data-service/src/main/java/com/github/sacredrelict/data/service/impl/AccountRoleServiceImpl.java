package com.github.sacredrelict.data.service.impl;

import com.github.sacredrelict.data.entity.AccountRole;
import com.github.sacredrelict.data.repository.AccountRoleRepository;
import com.github.sacredrelict.data.service.AccountRoleService;
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
     * Get Account Roles by Account id.
     * @param accountId - Account id.
     * @return list of Account Roles.
     */
    @Override
    public List<AccountRole> getRolesByAccountId(Long accountId) {
        return accountRoleRepository.findByAccountId(accountId);
    }

}
