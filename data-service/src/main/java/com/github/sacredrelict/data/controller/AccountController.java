package com.github.sacredrelict.data.controller;

import com.github.sacredrelict.data.entity.Account;
import com.github.sacredrelict.data.entity.AccountRole;
import com.github.sacredrelict.data.service.AccountRoleService;
import com.github.sacredrelict.data.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for account
 */
@RestController
@RequestMapping(path = "/account")
@Api(value = "account", description = "Operations with accounts.")
@PreAuthorize("#oauth2.hasScope('data')")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an account.")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "Update the account.")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.save(account));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete account by id.")
    public void deleteAccount(@RequestParam(value = "id") Long id) {
        accountService.delete(id);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "Find all accounts.")
    public List<Account> findAllAccounts() {
        return accountService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find account by id")
    public Account findAccountById(@RequestParam(value = "id") Long id) {
        return accountService.getById(id).get();
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "Find account by login.")
    public Account findAccountByLogin(@RequestParam(value = "login") String login) {
        return accountService.getByLogin(login);
    }

    @RequestMapping(path = "/role", method = RequestMethod.GET)
    @ApiOperation(value = "Find all account roles.")
    public List<AccountRole> findByAccountId(@RequestParam(value = "id") Long id) {
        return accountRoleService.getRolesByAccountId(id);
    }

}
