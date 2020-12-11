package com.ejbank.entities;

import javax.persistence.*;

@Entity
@Table(name = "ejbank_account_type")
public class AccountType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "rate", precision = 10, scale = 0)
    private double rate;

    @Column(name = "overdraft")
    private int overdraft;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getOverdraft() {
        return overdraft;
    }
}
