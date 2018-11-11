package com.github.sacredrelict.auth.service;

import com.github.sacredrelict.auth.domain.Account;

import java.util.Optional;

/**
 * Service layer interface for Account.
 */
public interface AccountService {

    Optional<Account> getById(Long id);

    Account getByLogin(String login);

}
