package com.ejbank.api.payload.account;

import com.ejbank.api.payload.account.AbstractAccount;
import com.ejbank.entities.Account;

/**
 * Used to make this result :
 *  {
 *      "amount": 150.0,
 *      "id": 4,
 *      "name": "Christophe Moreau",
 *      "type": "Courant"
 *  }
 */
public class AccountUserPayload extends AbstractAccount {

    private final String name;

    public AccountUserPayload(Account account) {
        super(account);
        this.name = account.getCustomer().getEntireName();
    }

    public String getName() {
        return name;
    }
}
