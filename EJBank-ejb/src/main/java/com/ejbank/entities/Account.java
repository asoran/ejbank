package com.ejbank.entities;

import javax.persistence.*;

@Entity
@Table(name = "ejbank_account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name="customer_id", nullable=true)
    private Customer customer;

    @ManyToOne(targetEntity = AccountType.class)
    @JoinColumn(name="account_type_id", nullable=true)
    private AccountType accountType;

    @Column(name = "balance", precision = 10, scale = 0)
    private double balance;

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
