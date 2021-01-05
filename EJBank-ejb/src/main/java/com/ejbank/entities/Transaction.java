package com.ejbank.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ejbank_transaction")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Account.class)
    @PrimaryKeyJoinColumn(name="account_from_id")
    private Account accountFrom;

    @ManyToOne(targetEntity = Account.class)
    @PrimaryKeyJoinColumn(name="account_from_id")
    private Account accountTo;

    @Column(name = "author")
    private int author;

    @Column(name = "amount", precision = 10, scale = 0)
    private double amount;

    @Column(name = "comment", length = 255)
    private String comment;

    @Column(name = "applied")
    private byte applied;

    @Column(name = "date")
    private Date date;

    public int getId() {
        return id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public int getAuthor() {
        return author;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public byte getApplied() {
        return applied;
    }

    public Date getDate() {
        return date;
    }
}