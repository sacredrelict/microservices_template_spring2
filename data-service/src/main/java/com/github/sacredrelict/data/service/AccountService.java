package com.github.sacredrelict.data.service;

import com.github.sacredrelict.data.entity.Account;

import java.util.List;
import java.util.Optional;

/**
 * Service layer interface for Account.
 */
public interface AccountService {

    Optional<Account> getById(Long id);

    Account getByLogin(String login);

    List<Account> getAll();

    Account save(Account account);

    void delete(Long id);

}
