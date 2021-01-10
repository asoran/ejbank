package com.ejbank.repositories;

import com.ejbank.entities.Advisor;

import javax.ejb.Stateless;

@Stateless
public class AdvisorRepository extends RepositoryImpl<Advisor>  {

    @Override
    public Class<Advisor> getClassT() {
        return Advisor.class;
    }

}
