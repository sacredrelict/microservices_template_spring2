package com.github.sacredrelict.api.common.component.security;

import com.github.sacredrelict.api.dto.Account;
import com.github.sacredrelict.api.dto.AccountRole;
import com.github.sacredrelict.api.service.AccountRoleService;
import com.github.sacredrelict.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service with security methods for the Account.
 */
@Component
public class SecurityService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    /**
     * Get current authorized account.
     * @return Account
     */
    public Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            return accountService.getByLogin(authentication.getName());
        }
        return null;
    }

    /**
     * Check is current authorized account has super admin role or not.
     * @return boolean
     */
    public boolean isAdmin(Account account) {
        if (account != null) {
            List<AccountRole> accountRoles = accountRoleService.getRolesByAccountId(account.getId());
            for (AccountRole accountRole : accountRoles) {
                if (accountRole.getRole().getName().equals("ROLE_ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }
}
