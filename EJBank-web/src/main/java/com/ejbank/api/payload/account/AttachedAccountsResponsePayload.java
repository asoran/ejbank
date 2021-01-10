package com.ejbank.api.payload.account;

import java.util.List;

public class AttachedAccountsResponsePayload {

    private final List<AttachedAccountPayload> accounts;

    private final String error;

    public AttachedAccountsResponsePayload(List<AttachedAccountPayload> accounts) {
        this.accounts = accounts;
        error = null;
    }

    public AttachedAccountsResponsePayload(String error) {
        this.accounts = null;
        this.error = error;
    }

    public List<AttachedAccountPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }

}
