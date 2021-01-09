package com.ejbank.haricots;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ejbank.entities.User;
import com.ejbank.repositories.UserRepository;

@Stateless
@LocalBean
public class UserBean implements Bean {

    @Inject
    private UserRepository userRepository;
    
    public User getUserById(int id) {
        return userRepository.getById(id);
    }
}
