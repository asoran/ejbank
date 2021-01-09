package com.ejbank.repositories;

import com.ejbank.entities.Account;
import com.ejbank.entities.Transaction;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AccountRepository extends RepositoryImpl<Account> {

    @Override
    public Class<Account> getClassT() {
        return Account.class;
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getAllSentTransactions(Account account) {
        return this.em.createQuery("SELECT t FROM Transaction t WHERE t.id = :id")
                .setParameter("id", account.getId())
                .getResultList();

    }

}
