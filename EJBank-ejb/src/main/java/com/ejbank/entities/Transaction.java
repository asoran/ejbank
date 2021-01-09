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
    @PrimaryKeyJoinColumn(name="account_id_from")
    private Account accountFrom;

    @ManyToOne(targetEntity = Account.class)
    @PrimaryKeyJoinColumn(name="account_id_to")
    private Account accountTo;

    @ManyToOne( targetEntity = User.class )
    @PrimaryKeyJoinColumn(name="author")
    private User author;

    @Column(name = "amount", precision = 10, scale = 0)
    private double amount;

    @Column(name = "comment", length = 255)
    private String comment;

    @Column(name = "applied")
    private boolean applied;

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

    public User getAuthor() {
        return author;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public boolean getApplied() {
        return applied;
    }

    public Date getDate() {
        return date;
    }
}
