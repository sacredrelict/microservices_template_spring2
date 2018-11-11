package com.github.sacredrelict.api.builder;

import com.github.sacredrelict.api.dto.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for Account.
 */
public class AccountBuilder {

    public Account createAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setActive(true);
        account.setEmail("adminTest@github.com");
        account.setLogin("adminTest");
        account.setPassword("adminTest");
        return account;
    }


    public Account createInvalidAccount() {
        Account account = new Account();
        account.setEmail("testtest");
        return account;
    }

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();

        Account account = new Account();
        account.setId(1L);
        account.setActive(true);
        account.setEmail("admin@github.com");
        account.setLogin("admin");
        account.setPassword("admin");
        accounts.add(account);

        Account account2 = new Account();
        account2.setId(2L);
        account2.setActive(true);
        account2.setEmail("admin2@github.com");
        account2.setLogin("admin2");
        account2.setPassword("admin2");
        accounts.add(account2);

        return accounts;
    }


}
