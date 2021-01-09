package com.ejbank.repositories;

import com.ejbank.entities.Transaction;

import javax.ejb.Stateless;

@Stateless
public class TransactionRepository extends RepositoryImpl<Transaction> {

    @Override
    public Class<Transaction> getClassT() {
        return Transaction.class;
    }

}
