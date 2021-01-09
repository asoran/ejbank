package com.ejbank.haricots;

import com.ejbank.entities.User;
import com.ejbank.repositories.UserRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class UserBean implements Bean {

    @Inject
    private UserRepository userRepository;

    public User getUserById(int id) {
        return this.userRepository.getById(id);
    }

}
