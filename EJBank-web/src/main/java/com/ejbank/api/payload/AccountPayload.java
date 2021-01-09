package com.ejbank.api.payload;

import com.ejbank.entities.Account;

/**
 * Used to make this result :
 * {
 *     "amount": 150.0,
 *     "id": 4,
 *     "type": "Courant"
 * }
 */
public class AccountPayload extends AbstractAccount {
    public AccountPayload(Account account) {
        super(account);
    }
}
