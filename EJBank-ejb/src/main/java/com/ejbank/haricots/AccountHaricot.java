package com.ejbank.haricots;

import com.ejbank.entities.Account;
import com.ejbank.entities.Customer;
import com.ejbank.entities.User;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.CustomerRepository;

import javax.ejb.Local;
import javax.inject.Inject;
import java.util.List;

@Local
public class AccountHaricot {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CustomerRepository customerRepository;

    public List<Account> getAccountsByUserId(int userId) {
        Customer customer = customerRepository.getById(userId);
        return customer.getAccounts();
    }
}
