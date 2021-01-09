package com.ejbank.haricots;

import com.ejbank.repositories.TransactionRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class TransactionBean {

	@Inject
	private TransactionRepository transactionRepository;


}
