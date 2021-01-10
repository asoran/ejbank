package com.ejbank.api.payload.account;

import com.ejbank.api.payload.account.AbstractAccount;
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
public class AttachedAccountPayload extends AbstractAccount {

    private final String name;

    private final long validation;

    public AttachedAccountPayload(Account account, long validation) {
        super(account);
        this.validation = validation;
        this.name = account.getCustomer().getEntireName();
    }

    public String getName() {
        return name;
    }

    public long getValidation() {
        return validation;
    }
}
