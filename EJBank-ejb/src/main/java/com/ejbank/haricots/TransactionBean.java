package com.ejbank.haricots;

import com.ejbank.entities.*;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.entities.Account;
import com.ejbank.entities.Transaction;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.TransactionRepository;
import com.ejbank.repositories.UserRepository;

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

	@Inject
	private UserRepository userRepository;

	public List<Transaction> getAllTransactionsByAccountId(int userId) {
		Account account = accountRepository.getById(userId);
		/* Transactions envoyées : */
		return accountRepository.getAllSentTransactions(account);
	}

	public Transaction addNewTransaction(int srcAccId, int destAccId, double amount, String comment, int authorId) {
		Account srcAcc = accountRepository.getById(srcAccId);
		Account destAcc = accountRepository.getById(destAccId);
		User user = userRepository.getById(authorId);

		return transactionRepository.add(new Transaction(srcAcc, destAcc, user, amount, comment));
	}

	public Transaction getTransactionsById(int transactionId) {
		return transactionRepository.getById(transactionId);
	}

	public Transaction update(Transaction transaction) {
		return transactionRepository.update(transaction);
	}
}
