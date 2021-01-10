package com.ejbank.haricots;

import com.ejbank.entities.Advisor;
import com.ejbank.repositories.AdvisorRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
@LocalBean
public class AdvisorBean implements Bean {

    @Inject
    private AdvisorRepository advisorRepository;

    public Optional<Advisor> getAdvisorById(int id) {
        return advisorRepository.getByidOpt(id);
    }

}
