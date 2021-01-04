package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
public class Customer extends User {

    @ManyToOne(targetEntity = Advisor.class)
    @PrimaryKeyJoinColumn(name="advisor_id")
    private Advisor advisor;

    @OneToMany( mappedBy = "customer" )
    private List<Account> accounts;

    public Customer() {}

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
