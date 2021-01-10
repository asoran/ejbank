package com.ejbank.haricots;

import com.ejbank.entities.Customer;
import com.ejbank.repositories.CustomerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
@LocalBean
public class CustomerBean implements Bean {

    @Inject
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomerById(int id) {
        return Optional.ofNullable(customerRepository.getById(id));
    }

}
