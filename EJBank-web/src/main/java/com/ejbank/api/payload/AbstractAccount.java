package com.ejbank.api.payload;

public abstract class AbstractAccount {

    protected final int id;

    protected final String type;

    protected final double amount;

    public AbstractAccount(int id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

}
