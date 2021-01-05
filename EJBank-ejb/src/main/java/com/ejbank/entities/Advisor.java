package com.ejbank.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue("advisor")
public class Advisor extends User {
}
