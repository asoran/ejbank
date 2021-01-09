package com.ejbank.test;

import com.ejbank.repositories.AccountRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class TestBean implements TestBeanLocal {

//    @Inject
//    private AccountRepository accountHaricot;

    @Override
    public String test() {
//        accountHaricot.getAll();
        return "Hello world from EJB";
    }
}
