package com.ejbank.api.payload.transaction;

import com.ejbank.entities.Transaction;

public class TransactionPayload {

    private static final String STATE_APPLIED = "APPLIED";
    private static final String STATE_TO_APPROVE = "TO_APPROVE";
    private static final String STATE_WAITING_APPROVE = "WAITING_APPROVE";

    private final int id;

    private final String date;

    private final String source;

    private final String destination;

    private final String destinationUser;

    private final double amount;

    private final String author;

    private final String comment;

    private final boolean state;

    private final String usertype;

    public TransactionPayload(Transaction transaction, String usertype) {
        this.id = transaction.getId();
        this.date = transaction.getDate().toString();
        this.source = transaction.getAccountFrom().getAccountType().getName();
        this.destination = transaction.getAccountTo().getAccountType().getName();
        this.destinationUser = transaction.getAccountTo().getCustomer().getFirstname();
        this.author = transaction.getAuthor().getEntireName();
        this.amount = transaction.getAmount();
        this.comment = transaction.getComment();
        this.state = transaction.getApplied();
        this.usertype = usertype;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public double getAmount() {
        return amount;
    }

    public String getAuthor() {
        return author;
    }

    public String getComment() {
        return comment;
    }

    public String getState() {
        if ( state ) return STATE_APPLIED;
        if ( usertype.equals("advisor") ) return STATE_TO_APPROVE;
        return STATE_WAITING_APPROVE;
    }
}
