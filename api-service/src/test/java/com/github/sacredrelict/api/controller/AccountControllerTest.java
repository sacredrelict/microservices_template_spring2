package com.github.sacredrelict.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sacredrelict.api.ApplicationTest;
import com.github.sacredrelict.api.builder.AccountBuilder;
import com.github.sacredrelict.api.client.DataServiceClient;
import com.github.sacredrelict.api.common.component.message.Messages;
import com.github.sacredrelict.api.common.component.security.SecurityService;
import com.github.sacredrelict.api.config.SpringSecurityWebAuxTestConfig;
import com.github.sacredrelict.api.dto.Account;
import com.github.sacredrelict.api.service.AccountRoleService;
import com.github.sacredrelict.api.service.AccountService;
import com.github.sacredrelict.api.util.annotation.WithMockOAuth2Scope;
import com.github.sacredrelict.api.validation.AccountValidation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for AccountController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        ApplicationTest.class,
        SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AccountController accountController;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private AccountValidation accountValidation;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountRoleService accountRoleService;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private DataServiceClient dataServiceClient;

    @MockBean
    private Messages messages;

    private AccountBuilder accountBuilder;
    private Account account;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        accountBuilder = new AccountBuilder();
        account = accountBuilder.createAccount();

        when(securityService.getCurrentAccount()).thenReturn(account);
        when(dataServiceClient.findAccountById(anyLong())).thenReturn(account);
        when(dataServiceClient.findAccountByLogin(anyString())).thenReturn(null);
    }

    @Test
    @WithUserDetails("admin")
    @WithMockOAuth2Scope(scope = "service")
    public void createAccountTest_addData_noException() throws Exception {
        this.mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().is(201));
    }

    @Test
    @WithUserDetails("admin")
    @WithMockOAuth2Scope(scope = "service")
    public void editAccountTest_start_noException() throws Exception {
        this.mockMvc.perform(put("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test")
    @WithMockOAuth2Scope(scope = "service")
    public void editAccountTest_forNotAdminRole_accessDenied() {
        Exception exception = null;
        try {
            this.mockMvc.perform(put("/api/account")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(account)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            exception = e;
        }
        assertThat(exception).isNotNull();
        assertTrue(exception.getCause() instanceof AccessDeniedException);
    }

    @Test
    @WithUserDetails("admin")
    @WithMockOAuth2Scope(scope = "service")
    public void editAccountTest_start_invalidFields() throws Exception {
        account = accountBuilder.createInvalidAccount();
        this.mockMvc.perform(put("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("email")));
    }

    @Test
    @WithMockOAuth2Scope(scope = "data")
    public void editAccountTest_start_accessDenied() {
        Exception exception = null;
        try {
            this.mockMvc.perform(put("/api/account")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(account)))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("success")));
        } catch (Exception e) {
            exception = e;
        }
        assertThat(exception).isNotNull();
        assertTrue(exception.getCause() instanceof AccessDeniedException);
    }

    @Test
    @WithUserDetails("admin")
    @WithMockOAuth2Scope(scope = "service")
    public void deleteAccountTest_deleteData_noException() throws Exception {
        this.mockMvc.perform(delete("/api/account?id=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "service")
    public void editCurrentAccountTest_start_noException() throws Exception {
        account = accountBuilder.createAccount();
        this.mockMvc.perform(put("/api/account/current")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "service")
    public void getListOfAccountsTest_getList_noException() throws Exception {
        mockMvc.perform(get("/api/account/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "service")
    public void getAccountByIdTest_getById_noException() throws Exception {
        mockMvc.perform(get("/api/account?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "service")
    public void getAccountByLoginTest_getByLogin_noException() throws Exception {
        mockMvc.perform(get("/api/account/login?login=adminTest")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2Scope(scope = "service")
    public void getRolesByAccountIdTest_getRoles_noException() throws Exception {
        mockMvc.perform(get("/api/account/role?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
