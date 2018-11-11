package com.github.sacredrelict.data.repository;

import com.github.sacredrelict.data.entity.AccountRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for AccountRole entity.
 */
public interface AccountRoleRepository extends CrudRepository<AccountRole, Long> {

    List<AccountRole> findByAccountId(Long accountId);

}
