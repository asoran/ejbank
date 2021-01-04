package com.ejbank.repositories;

import com.ejbank.entities.Transaction;

public class TransactionRepository extends RepositoryImpl<Transaction> {

    @Override
    public Class<Transaction> getClassT() {
        return Transaction.class;
    }

}
