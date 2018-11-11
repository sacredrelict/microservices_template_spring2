package com.github.sacredrelict.data.repository;

import com.github.sacredrelict.data.ApplicationTest;
import com.github.sacredrelict.data.buider.AccountBuilder;
import com.github.sacredrelict.data.buider.AccountRoleBuilder;
import com.github.sacredrelict.data.buider.RoleBuilder;
import com.github.sacredrelict.data.entity.Account;
import com.github.sacredrelict.data.entity.AccountRole;
import com.github.sacredrelict.data.entity.Role;
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

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test AccountRoleRepository.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        ApplicationTest.class})
@Transactional
public class AccountRoleRepositoryTest {

    @Autowired
    @InjectMocks
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private AccountRepository accountRepository;

    private AccountBuilder accountBuilder;
    private Account account;
    private RoleBuilder roleBuilder;
    private Role role;
    private AccountRoleBuilder accountRoleBuilder;
    private AccountRole accountRole;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        accountBuilder = new AccountBuilder();
        account = accountBuilder.createAccount();
        roleBuilder = new RoleBuilder();
        role = roleBuilder.createRole();
        accountRoleBuilder = new AccountRoleBuilder();
        accountRole = accountRoleBuilder.createAccountRole(account, role);
    }

    @Test
    @Rollback
    public void saveTest_createAccountRole_noException() {
        Account testAccount = accountRepository.save(account);
        assertNotNull(testAccount);

        accountRole.setAccount(testAccount);
        AccountRole testAccountRole = accountRoleRepository.save(accountRole);
        assertNotNull(testAccountRole);
        assertNotNull(testAccountRole.getId());
        assertEquals(testAccountRole.getAccount(), testAccount);
    }

    @Test
    @Rollback
    public void deleteTest_deleteAccountRole_noException() {
        Account testAccount = accountRepository.save(account);
        assertNotNull(testAccount);

        accountRole.setAccount(testAccount);
        AccountRole testAccountRole = accountRoleRepository.save(accountRole);
        assertNotNull(testAccountRole);
        assertNotNull(testAccountRole.getId());

        accountRoleRepository.delete(testAccountRole);
        assertNull(accountRoleRepository.findById(testAccountRole.getId()));
    }

    @Test
    @Rollback
    public void findByAccountIdTest_findAccountRoleByAccountId_noException() {
        Account testAccount = accountRepository.save(account);
        assertNotNull(testAccount);

        accountRole.setAccount(testAccount);
        AccountRole testAccountRole = accountRoleRepository.save(accountRole);
        assertNotNull(testAccountRole);
        assertNotNull(testAccountRole.getAccount());

        List<AccountRole> accountRoleList = accountRoleRepository.findByAccountId(testAccountRole.getAccount().getId());
        assertNotNull(accountRoleList);
        assertTrue(accountRoleList.size() > 0);
    }

}
