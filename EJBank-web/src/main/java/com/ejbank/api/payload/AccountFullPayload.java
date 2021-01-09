package com.ejbank.api.payload;

import com.ejbank.entities.Account;

public class AccountFullPayload extends AbstractAccount {

    private final String name;

    private final int validation;

    public AccountFullPayload(int id, String type, double amount, String name, int validation) {
        super(id, type, amount);

        this.name = name;
        this.validation = validation;
    }

    /* TODO ; Change the validation number when found. */
    public static AccountFullPayload build(Account account) {
        return new AccountFullPayload(account.getId(), account.getAccountType().getName(),
                account.getBalance(), account.getCustomer().getEntireName(), 0);
    }

    public String getName() {
        return name;
    }

    public int getValidation() {
        return validation;
    }
}
