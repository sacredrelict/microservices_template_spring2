package com.github.sacredrelict.data.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sacredrelict.data.ApplicationTest;
import com.github.sacredrelict.data.buider.AccountBuilder;
import com.github.sacredrelict.data.entity.Account;
import com.github.sacredrelict.data.service.AccountRoleService;
import com.github.sacredrelict.data.service.AccountService;
import com.github.sacredrelict.data.util.annotation.WithMockOAuth2Scope;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for AccountController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AccountController accountController;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountRoleService accountRoleService;

    private AccountBuilder accountBuilder;
    private Account account;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        accountBuilder = new AccountBuilder();
        account = accountBuilder.createAccount();
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void findAccountTest_findAccount_noException() throws Exception {
        this.mockMvc.perform(get("/account?id=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "service")
    public void findAccountTest_findAccount_accessDenied() {
        Exception exception = null;
        try {
            this.mockMvc.perform(get("/account?id=10")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            exception = e;
        }
        assertThat(exception).isNotNull();
        assertTrue(exception.getCause() instanceof AccessDeniedException);
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void createAccountTest_addData_noException() throws Exception {
        String json = objectMapper.writeValueAsString(account);
        this.mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is(201));
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void updateAccountTest_updateData_noException() throws Exception {
        String json = objectMapper.writeValueAsString(account);
        this.mockMvc.perform(put("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void deleteAccountTest_deleteData_noException() throws Exception {
        this.mockMvc.perform(delete("/account?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void findAccountByLoginTest_findAccountByLogin_noException() throws Exception {
        this.mockMvc.perform(get("/account/login?login=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void findAllAccountsTest_findAccounts_noException() throws Exception {
        this.mockMvc.perform(get("/account/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void findByAccountIdTest_findAccountRoles_noException() throws Exception {
        this.mockMvc.perform(get("/account/role?id=12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
