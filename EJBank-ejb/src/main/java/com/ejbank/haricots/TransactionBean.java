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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
@LocalBean
public class TransactionBean {

	@Inject
	private TransactionRepository transactionRepository;

	@Inject
	private AccountRepository accountRepository;

	@Inject
	private UserRepository userRepository;

	private Stream<Transaction> initializeTransactionsStream(int userId) {
		Account account = accountRepository.getById(userId);
		List<Transaction> transactions =  accountRepository.getAllSentTransactions(account);
		transactions.addAll(accountRepository.getAllReceivedTransactions(account));
		return transactions.stream();
	}

	public List<Transaction> getAllTransactionsByAccountId(int userId) {
		return initializeTransactionsStream(userId).collect(Collectors.toList());
	}

	public List<Transaction> getAllTransactionsByAccountId(int userId, int limit) {
		return initializeTransactionsStream(userId).limit(limit + 10).collect(Collectors.toList());
	}

	public Transaction addNewTransaction(int srcAccId, int destAccId, double amount, String comment, int authorId, boolean applied) {
		Account srcAcc = accountRepository.getById(srcAccId);
		Account destAcc = accountRepository.getById(destAccId);
		User user = userRepository.getById(authorId);

		return transactionRepository.add(new Transaction(srcAcc, destAcc, user, amount, comment, applied));
	}

	public Transaction getTransactionsById(int transactionId) {
		return transactionRepository.getById(transactionId);
	}

	public Transaction update(Transaction transaction) {
		return transactionRepository.update(transaction);
	}
}
