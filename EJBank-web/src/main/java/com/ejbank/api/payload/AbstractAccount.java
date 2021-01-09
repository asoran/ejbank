package com.ejbank.api.payload;

import com.ejbank.entities.Account;

public abstract class AbstractAccount {

    protected final int id;

    protected final String type;

    protected final double amount;

    public AbstractAccount(Account account) {
        this.id = account.getId();
        this.type = account.getAccountType().getName();
        this.amount = account.getBalance();
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

}
