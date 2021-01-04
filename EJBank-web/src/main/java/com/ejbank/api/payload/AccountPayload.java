package com.ejbank.api.payload;

import com.ejbank.entities.Account;

public class AccountPayload {

    private final int id;

    private final String type;

    private final double amount;

    public AccountPayload(int id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public static AccountPayload build(Account account) {
        return new AccountPayload(account.getId(), account.getAccountType().getName(), account.getBalance());
    }
}
