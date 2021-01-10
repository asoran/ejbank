package com.ejbank.haricots;

import com.ejbank.entities.Account;
import com.ejbank.entities.Advisor;
import com.ejbank.entities.Customer;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.AdvisorRepository;
import com.ejbank.repositories.CustomerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class AccountBean implements Bean {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private AdvisorRepository advisorRepository;

    public List<Account> getAccountsByCustomerId(int userId) {
        Customer customer = customerRepository.getById(userId);
        /* TODO : NPE */
        return customer.getAccounts();
    }

    public List<Account> getAccountsByAdvisorId(int advisorId) {
        Advisor advisor = advisorRepository.getById(advisorId);
        /* TODO : NPE */
        List<Customer> customers = advisor.getAttachedCustomers();
        return customers.stream().flatMap( c -> c.getAccounts().stream() ).collect(Collectors.toList());
    }

    /* TODO : do this method*/
    public double computeInterest(Account account) {
        return 42.0;
    }

    public Account getAccountById(int id) {
        return this.accountRepository.getById(id);
    }
}
