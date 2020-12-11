package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
public class Customer extends User {

    @ManyToOne(targetEntity = Advisor.class)
    @JoinColumn(name="advisor_id", nullable=true) /* todo : pass to false when possible */
    private Advisor advisor;

    @OneToMany( mappedBy = "customer" )
    private List<Account> accounts;

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

    public List<Account> getAccounts() {
        return accounts;
    }
}
