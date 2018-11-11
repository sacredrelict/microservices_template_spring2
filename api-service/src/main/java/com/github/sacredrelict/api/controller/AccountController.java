package com.github.sacredrelict.api.controller;

import com.github.sacredrelict.api.common.component.message.Messages;
import com.github.sacredrelict.api.common.component.security.SecurityService;
import com.github.sacredrelict.api.dto.Account;
import com.github.sacredrelict.api.dto.AccountRole;
import com.github.sacredrelict.api.service.AccountRoleService;
import com.github.sacredrelict.api.service.AccountService;
import com.github.sacredrelict.api.validation.AccountValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controller for account
 */
@RestController
@RequestMapping(path = "/account")
@Api(value = "account", description = "Operations with accounts.")
@PreAuthorize("#oauth2.hasScope('api')")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountValidation accountValidation;

    @Autowired
    private Messages messages;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an account.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createAccount(@RequestBody @Valid Account account, BindingResult bindingResult){
        Map<String, String> fieldErrorsMap = accountValidation.validateAccountForCreate(bindingResult, account);

        if (fieldErrorsMap.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorsMap);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Edit an account.", response = Iterable.class)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editAccount(@RequestBody @Valid Account account, BindingResult bindingResult) {
        Map<String, String> fieldErrorsMap = accountValidation.validateAccountForUpdate(bindingResult, account);

        if (fieldErrorsMap.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorsMap);
        }

        return ResponseEntity.ok(accountService.editAccount(account));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete an account.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAccount(@RequestParam(value = "id", required = true) Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(messages.get("account.deleted"));
    }

    @RequestMapping(path = "/current", method = RequestMethod.PUT)
    @ApiOperation(value = "Edit the current account.", response = Iterable.class)
    public ResponseEntity<?> editCurrentAccount(@RequestBody @Valid Account account, BindingResult bindingResult) {
        Account currentAccount = securityService.getCurrentAccount();

        if (account.getId() == null || currentAccount.getId() != account.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messages.get("account.not.found"));
        }

        Map<String, String> fieldErrorsMap = accountValidation.validateAccountForUpdate(bindingResult, account);

        if (fieldErrorsMap.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrorsMap);
        }

        return ResponseEntity.ok(accountService.editAccount(account));
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get list of accounts.", response = Iterable.class)
    public ResponseEntity<List<Account>> getListOfAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find account by id.")
    public ResponseEntity<Account> getAccountById(@RequestParam(value = "id", required = true) Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getById(id));
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "Get account by login.")
    public ResponseEntity<Account> getAccountByLogin(@RequestParam(value = "login") String login) {
        return ResponseEntity.ok(accountService.getByLogin(login));
    }

    @RequestMapping(path = "/role", method = RequestMethod.GET)
    @ApiOperation(value = "Get all account roles.")
    public ResponseEntity<List<AccountRole>> getRolesByAccountId(@RequestParam(value = "id", required = true) Long id) {
        return ResponseEntity.ok(accountRoleService.getRolesByAccountId(id));
    }

}
