package com.github.sacredrelict.api.services;

import com.github.sacredrelict.api.ApplicationTest;
import com.github.sacredrelict.api.builder.AccountBuilder;
import com.github.sacredrelict.api.client.DataServiceClient;
import com.github.sacredrelict.api.common.component.security.SecurityService;
import com.github.sacredrelict.api.dto.Account;
import com.github.sacredrelict.api.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Tests for AccountService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class AccountServiceTest {

    @Autowired
    @InjectMocks
    private AccountService accountService;

    @Autowired
    @InjectMocks
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityService securityService;

    @Mock
    private DataServiceClient dataServiceClient;

    private AccountBuilder accountBuilder;
    private Account account;
    private List<Account> accounts;
    private ResponseEntity<Account> responseEntity;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        accountBuilder = new AccountBuilder();
        account = accountBuilder.createAccount();
        accounts = accountBuilder.getAccounts();
        responseEntity = new ResponseEntity<>(account, HttpStatus.OK);

        when(securityService.getCurrentAccount()).thenReturn(account);
        when(dataServiceClient.findAccountsByCompany(anyLong())).thenReturn(accounts);
        when(dataServiceClient.findAccountById(anyLong())).thenReturn(account);
        when(dataServiceClient.updateAccount(any(Account.class))).thenReturn(responseEntity);
    }

    @Test
    public void getListOfAccountsTest_start_noException() throws Exception {
        List<Account> accountList = accountService.getListOfAccounts();
        assertThat(accountList).isNotNull();
    }

    @Test
    public void editAccountTest_start_noException() throws Exception {
        Account accountToEdit = accountService.editAccount(account);
        assertThat(accountToEdit.getEmail()).isEqualTo("adminTest@github.com");
    }

}
