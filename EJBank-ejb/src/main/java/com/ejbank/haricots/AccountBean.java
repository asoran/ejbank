package com.ejbank.haricots;

import com.ejbank.entities.Account;
import com.ejbank.entities.Advisor;
import com.ejbank.entities.Customer;
import com.ejbank.entities.User;
import com.ejbank.repositories.AccountRepository;
import com.ejbank.repositories.AdvisorRepository;
import com.ejbank.repositories.CustomerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

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

    public long getValidationNumberOfAccount(Account account) {
        return accountRepository.countAllSentTransaction(account);
    }

    public double computeInterest(Account account) {
        return account.getBalance() * (account.getAccountType().getRate() / 100f);
    }

    public Optional<Account> getAccountById(int id) {
        return Optional.ofNullable(this.accountRepository.getById(id));
    }

    public boolean checkIfUserHasAccessToAccount(User user, Account account) {
        if ( account.getCustomer().getId() == user.getId() ) return true;
        if ( user.getType().equals("customer") ) return false;

        if ( account.getCustomer().getAdvisor().getId() == user.getId() ) return true;
        return false;
    }
}
