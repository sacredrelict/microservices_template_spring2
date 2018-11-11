package com.github.sacredrelict.data.repository;

import com.github.sacredrelict.data.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for Account entity.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByLogin(String login);

}
