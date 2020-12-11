package com.ejbank.entities;

import javax.persistence.*;

@Entity
@Table(name = "ejbank_user")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
abstract public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected int id;

    @Column(name = "login", length = 8)
    protected String login;

    @Column(name = "password")
    protected String password;

    @Column(name = "email")
    protected String email;

    @Column(name = "firstname", length = 50)
    protected String firstname;

    @Column(name = "lastname", length = 50)
    protected String lastname;

//    @Column(name = "type", length = 50)
//    private String type;


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
