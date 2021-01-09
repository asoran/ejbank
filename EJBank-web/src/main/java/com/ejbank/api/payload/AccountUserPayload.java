package com.ejbank.api.payload;

import com.ejbank.entities.Account;

public class AccountUserPayload extends AbstractAccount {

    private final String name;

    public AccountUserPayload(int id, String type, double amount, String name) {
        super(id, type, amount);

        this.name = name;
    }

    public static AccountUserPayload build(Account account) {
        return new AccountUserPayload(account.getId(), account.getAccountType().getName(),
                account.getBalance(), account.getCustomer().getEntireName());
    }

    public String getName() {
        return name;
    }
}
