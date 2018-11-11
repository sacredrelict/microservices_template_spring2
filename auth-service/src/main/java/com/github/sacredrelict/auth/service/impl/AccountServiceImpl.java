package com.github.sacredrelict.auth.service.impl;

import com.github.sacredrelict.auth.domain.Account;
import com.github.sacredrelict.auth.repositories.AccountRepository;
import com.github.sacredrelict.auth.security.PasswordEncoder;
import com.github.sacredrelict.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer implementation for Account.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Search Account by id.
     * @param id - Account id.
     * @return Account.
     */
    @Override
    public Optional<Account> getById(Long id) {

        return accountRepository.findById(id);
    }

    /**
     * Search Account by login.
     * @param login- Account login.
     * @return Account.
     */
    @Override
    public Account getByLogin(String login) {

        return accountRepository.findByLogin(login);
    }

}
