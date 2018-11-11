package com.github.sacredrelict.data.buider;

import com.github.sacredrelict.data.entity.Account;
import com.github.sacredrelict.data.entity.AccountRole;
import com.github.sacredrelict.data.entity.Role;

/**
 * Builder for AccountRole.
 */
public class AccountRoleBuilder {

    public AccountRole createAccountRole(Account account, Role role) {
        AccountRole accountRole = new AccountRole();
        accountRole.setId(11L);
        accountRole.setAccount(account);
        accountRole.setRole(role);
        return accountRole;
    }

}
