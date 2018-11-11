package com.github.sacredrelict.data.repository;

import com.github.sacredrelict.data.ApplicationTest;
import com.github.sacredrelict.data.buider.AccountBuilder;
import com.github.sacredrelict.data.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Test AccountRepository.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        ApplicationTest.class})
@Transactional
public class AccountRepositoryTest {

    @Autowired
    @InjectMocks
    private AccountRepository accountRepository;

    private AccountBuilder accountBuilder;
    private Account account;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        accountBuilder = new AccountBuilder();
        account = accountBuilder.createAccount();
    }

    @Test
    @Rollback
    public void saveTest_createAccount_noException() {
        Account testAccount = accountRepository.save(account);

        assertNotNull(testAccount);
        assertNotNull(testAccount.getId());
        assertEquals(testAccount.getLogin(), account.getLogin());
        assertEquals(testAccount.getEmail(), account.getEmail());
    }

    @Test
    @Rollback
    public void deleteTest_deleteAccount_noException() {
        Account testAccount = accountRepository.save(account);
        assertNotNull(testAccount);
        assertNotNull(testAccount.getId());

        accountRepository.delete(testAccount);
        assertNull(accountRepository.findById(testAccount.getId()));
    }

    @Test
    @Rollback
    public void findByLoginTest_findAccountByLogin_noException() {
        Account testAccount = accountRepository.save(account);
        assertNotNull(testAccount);
        assertNotNull(testAccount.getLogin());

        Account account = accountRepository.findByLogin(testAccount.getLogin());
        assertNotNull(account);
    }

}
