package com.ejbank.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ejbank_advisor")
public class Customer extends User {

    @ManyToOne(targetEntity = Advisor.class)
    @JoinColumn(name="advisor_id", nullable=true) /* todo : pass to false when possible */
    private Advisor advisor;

    public Customer(String login, String password, String email, String firstname, String lastname, Advisor advisor) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.advisor = advisor;
    }


    public Advisor getAdvisor() {
        return advisor;
    }
}
