package com.github.sacredrelict.api.service.impl;

import com.github.sacredrelict.api.client.DataServiceClient;
import com.github.sacredrelict.api.common.component.security.SecurityService;
import com.github.sacredrelict.api.dto.Account;
import com.github.sacredrelict.api.service.AccountService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Service layer implementation for Account.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private DataServiceClient dataServiceClient;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Get Accounts list.
     * @return Accounts list.
     */
    @Override
    public List<Account> getAllAccounts(){
        return dataServiceClient.findAllAccounts();
    }

    /**
     * Get Account by Id.
     * @param id - Account id.
     * @return Account object.
     */
    @Override
    public Account getById(Long id) {
        return dataServiceClient.findAccountById(id);
    }

    /**
     * Get Account by login.
     * @param login - Account login.
     * @return Account object.
     */
    @Override
    public Account getByLogin(String login) {
        return dataServiceClient.findAccountByLogin(login);
    }

    /**
     * Get list of accounts.
     * If current user is main admin, he will see all users.
     *
     * @return List of Accounts
     */
    @Override
    public List<Account> getListOfAccounts() {
        Account account = securityService.getCurrentAccount();
        if (securityService.isAdmin(account)) {
            return Lists.newArrayList(dataServiceClient.findAllAccounts());
        }

        return null;
    }

    /**
     * Create an account.
     * @param account - Account object.
     * @return created Account.
     */
    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        ResponseEntity<Account> responseEntity = dataServiceClient.createAccount(account);
        if (responseEntity == null) {
            return null;
        }

        return responseEntity.getBody();
    }

    /**
     * Edit account.
     * @param account - contain editable fields by user.
     * @return Account.
     */
    @Override
    public Account editAccount(Account account) {
        Account oldAccount = dataServiceClient.findAccountById(account.getId());

        if (oldAccount == null) {
            return null;
        }

        if (!StringUtils.isEmpty(account.getEmail())) {
            oldAccount.setEmail(account.getEmail());
        }

        if (!StringUtils.isEmpty(account.getPassword())) {
            oldAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        }

        ResponseEntity<Account> responseEntity = dataServiceClient.updateAccount(oldAccount);
        if (responseEntity == null) {
            return null;
        }

        return responseEntity.getBody();
    }

    /**
     * Delete account.
     * @param id - Account id.
     */
    @Override
    public void deleteAccount(Long id) {
        dataServiceClient.deleteAccount(id);
    }

}
