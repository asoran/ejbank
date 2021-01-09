package com.ejbank.api.payload;

import com.ejbank.entities.Account;

public class AccountPayload extends AbstractAccount {

    public AccountPayload(int id, String type, double amount) {
        super(id, type, amount);
    }

    public static AccountPayload build(Account account) {
        return new AccountPayload(account.getId(), account.getAccountType().getName(), account.getBalance());
    }
}
