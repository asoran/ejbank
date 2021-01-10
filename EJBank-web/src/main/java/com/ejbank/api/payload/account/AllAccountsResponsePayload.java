package com.ejbank.api.payload.account;

import java.util.List;

public class AllAccountsResponsePayload {

    private final List<AccountForTransactionPayload> accounts;

    private final String error;

    public AllAccountsResponsePayload(List<AccountForTransactionPayload> accounts) {
        this.accounts = accounts;
        error = null;
    }

    public AllAccountsResponsePayload(String error) {
        this.accounts = null;
        this.error = error;
    }

    public List<AccountForTransactionPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }
}
