package com.github.sacredrelict.auth.repositories;

import com.github.sacredrelict.auth.domain.AccountRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for AccountRole entity.
 */
public interface AccountRoleRepository extends CrudRepository<AccountRole, Long> {

    List<AccountRole> findByAccountId(Long accountId);

}
