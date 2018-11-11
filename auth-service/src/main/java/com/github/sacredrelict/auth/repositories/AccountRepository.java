package com.github.sacredrelict.auth.repositories;

import com.github.sacredrelict.auth.domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Account entity.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByLogin(String login);

}
