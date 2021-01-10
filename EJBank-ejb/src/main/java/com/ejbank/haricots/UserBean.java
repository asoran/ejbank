package com.ejbank.haricots;

import com.ejbank.entities.Advisor;
import com.ejbank.entities.Customer;
import com.ejbank.entities.User;
import com.ejbank.repositories.AdvisorRepository;
import com.ejbank.repositories.CustomerRepository;
import com.ejbank.repositories.UserRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
@LocalBean
public class UserBean implements Bean {

    @Inject
    private UserRepository userRepository;

    @Inject
    private AdvisorRepository advisorRepository;

    @Inject
    private CustomerRepository customerRepository;

    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(this.userRepository.getById(id));
    }

    public Advisor recoverAdvisorFromUser(User user) {
        if ( !user.getType().equals("advisor") ) {
            throw new IllegalArgumentException("This user is not an advisor !");
        }

        Advisor advisor = advisorRepository.getById(user.getId());
        if ( advisor == null ) {
            throw new IllegalStateException("The data are not coherent !");
        }

        return advisor;
    }

    public Customer recoverCustomerFromUser(User user) {
        if ( !user.getType().equals("customer") ) {
            throw new IllegalArgumentException("The user is not a customer !");
        }

        Customer customer = customerRepository.getById(user.getId());
        if ( customer == null ) {
            throw new IllegalStateException("The data are not coherent !");
        }

        return customer;
    }
}
