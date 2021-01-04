package com.ejbank.haricots;

import com.ejbank.entities.Account;
import com.ejbank.entities.Customer;
import com.ejbank.entities.User;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.CustomerRepository;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class AccountBean implements Bean {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    @Inject
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Account> getAccountsByCustomerId(int userId) {
        Customer customer = customerRepository.getById(userId);
        return customer.getAccounts();
    }
}
