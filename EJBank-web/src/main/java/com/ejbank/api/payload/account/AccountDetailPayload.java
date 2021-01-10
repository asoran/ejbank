package com.ejbank.api.payload.account;

import com.ejbank.entities.Account;

public class AccountDetailPayload {

    private final String owner;

    private final String advisor;

    private final double rate;

    private final double interest;

    private final double amount;

    private final String error;

    public AccountDetailPayload(Account account, double interest) {
        this.interest = interest;
        this.owner = account.getCustomer().getEntireName();
        this.advisor = account.getCustomer().getAdvisor().getEntireName();
        this.rate = account.getAccountType().getRate();
        this.amount = account.getBalance();
        this.error = null;
    }

    public AccountDetailPayload(String error) {
        this.error = error;
        this.owner = "";
        this.advisor = "";
        this.rate = 0;
        this.interest = 0;
        this.amount = 0;
    }

    public String getOwner() {
        return owner;
    }

    public String getAdvisor() {
        return advisor;
    }

    public double getRate() {
        return rate;
    }

    public double getInterest() {
        return interest;
    }

    public double getAmount() {
        return amount;
    }

    public String getError() {
        return error;
    }
}
