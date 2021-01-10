package com.ejbank.api.payload.account;

import com.ejbank.entities.Account;
import com.ejbank.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAccountsResponsePayload {

    private final List<AccountPayload> accounts;

    private final String error;

    public CustomerAccountsResponsePayload(List<AccountPayload> accounts) {
        this.accounts = accounts;
        error = null;
    }

    public CustomerAccountsResponsePayload(String error) {
        this.accounts = null;
        this.error = error;
    }

    public List<AccountPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
