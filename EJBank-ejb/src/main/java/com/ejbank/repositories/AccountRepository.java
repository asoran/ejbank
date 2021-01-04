package com.ejbank.repositories;

import com.ejbank.entities.Account;

import javax.ejb.Stateless;

@Stateless
public class AccountRepository extends RepositoryImpl<Account> {

    @Override
    public Class<Account> getClassT() {
        return Account.class;
    }

}
