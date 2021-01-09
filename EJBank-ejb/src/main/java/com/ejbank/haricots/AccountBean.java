package com.ejbank.haricots;

import com.ejbank.entities.Account;
import com.ejbank.entities.Customer;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.CustomerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class AccountBean implements Bean {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CustomerRepository customerRepository;

    public List<Account> getAccountsByCustomerId(int userId) {
        Customer customer = customerRepository.getById(userId);
        return customer.getAccounts();
    }

    /* TODO : do this method*/
    public double computeInterest(Account account) {
        return 42.0;
    }

    public Account getAccountById(int id) {
        return this.accountRepository.getById(id);
    }
}
