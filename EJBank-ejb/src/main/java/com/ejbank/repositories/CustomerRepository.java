package com.ejbank.repositories;

import com.ejbank.entities.Customer;

import javax.ejb.Stateless;

@Stateless
public class CustomerRepository extends RepositoryImpl<Customer> {

    @Override
    public Class<Customer> getClassT() {
        return Customer.class;
    }

}
