package com.ejbank.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue("advisor")
public class Advisor extends User {

    @OneToMany( mappedBy = "advisor" )
    private List<Customer> attachedCustomers;

    public List<Customer> getAttachedCustomers() {
        return attachedCustomers;
    }

}
