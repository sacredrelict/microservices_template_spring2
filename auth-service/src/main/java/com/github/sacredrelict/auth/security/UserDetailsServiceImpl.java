package com.github.sacredrelict.auth.security;

import com.github.sacredrelict.auth.common.Messages;
import com.github.sacredrelict.auth.domain.Account;
import com.github.sacredrelict.auth.domain.AccountRole;
import com.github.sacredrelict.auth.service.AccountRoleService;
import com.github.sacredrelict.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing of UserDetailsService interface.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private Messages messages;

    /**
     * Implementing method for Spring Security which get Account by login.
     * Put it into UserDetails.
     * @param username - username in String.
     * @return UserDetails object.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountService.getByLogin(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format(messages.get("security.auth.login.not.exists"), username));
        } else {
            List<AccountRole> accountRoleList = accountRoleService.getRolesByAccountId(account.getId());
            List<GrantedAuthority> authorities = new ArrayList<>();
            accountRoleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
            });

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    account.getLogin(), account.getPassword(), authorities);

            return userDetails;
        }
    }

}
