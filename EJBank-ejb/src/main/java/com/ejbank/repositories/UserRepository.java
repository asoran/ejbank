package com.ejbank.repositories;

import com.ejbank.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepository extends RepositoryImpl<User> {

    @Override
    public Class<User> getClassT() {
        return User.class;
    }



}
