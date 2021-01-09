package com.ejbank.haricots;

import com.ejbank.entities.Account;
import com.ejbank.entities.Customer;
import com.ejbank.entities.Transaction;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.TransactionRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class TransactionBean {

	@Inject
	private TransactionRepository transactionRepository;

	@Inject
	private AccountRepository accountRepository;

	public List<Transaction> getAllTransactionsByAccountId(int userId) {
		Account account = accountRepository.getById(userId);
		/* Transactions envoyées : */
		return accountRepository.getAllSentTransactions(account);
	}
}
