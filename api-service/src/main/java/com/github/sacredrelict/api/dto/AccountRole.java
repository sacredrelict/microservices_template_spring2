package com.github.sacredrelict.api.dto;

import java.io.Serializable;

public class AccountRole implements Serializable {

    private Long id;
    private Account account;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountRole{" +
                "id=" + id +
                ", account.id=" + account.getId() +
                ", role.id=" + role.getId() +
                '}';
    }

}
