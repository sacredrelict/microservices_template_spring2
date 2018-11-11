package com.github.sacredrelict.data.service.impl;

import com.github.sacredrelict.data.entity.Account;
import com.github.sacredrelict.data.repository.AccountRepository;
import com.github.sacredrelict.data.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer implementation for Account.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get all Accounts.
     * @return list of Accounts.
     */
    @Override
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }

    /**
     * Get Account by id.
     * @param id - Account id.
     * @return Account object.
     */
    @Override
    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * Get Account by login.
     * @param login - Account login.
     * @return Account object.
     */
    @Override
    public Account getByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    /**
     * Save Account.
     * @param account - Account entity.
     * @return Account object.
     */
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Delete Account by id.
     * @param id
     */
    @Override
    public void delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account != null) {
            accountRepository.delete(account.get());
        }
    }

}
