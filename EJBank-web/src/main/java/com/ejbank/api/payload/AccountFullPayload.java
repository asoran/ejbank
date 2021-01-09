package com.ejbank.api.payload;

import com.ejbank.entities.Account;

/**
 * Used to make this result :
 * {
 *     "amount": 150.0,
 *     "id": 4,
 *     "name": "Christophe Moreau",
 *     "type": "Courant",
 *     "validation": 0
 * }
 */
public class AccountFullPayload extends AbstractAccount {

    private final String name;

    private final int validation;

    public AccountFullPayload(Account account) {
        super(account);

        this.name = account.getCustomer().getEntireName();
        /* TODO ; Change the validation number when found. */
        this.validation = 0;
    }

    public String getName() {
        return name;
    }

    public int getValidation() {
        return validation;
    }
}
