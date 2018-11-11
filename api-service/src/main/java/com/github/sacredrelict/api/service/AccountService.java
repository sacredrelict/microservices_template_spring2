package com.github.sacredrelict.api.service;

import com.github.sacredrelict.api.dto.Account;

import java.util.List;

/**
 * Service layer interface for Account.
 */
public interface AccountService {

    List<Account> getAllAccounts();

    Account getById(Long id);

    Account getByLogin(String login);

    List<Account> getListOfAccounts();

    Account createAccount(Account account);

    Account editAccount(Account accountDto);

    void deleteAccount(Long id);

}
