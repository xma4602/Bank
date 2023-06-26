package com.bank.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    protected UUID id;
    @Column(name = "name")
    String name;

    @Column(name = "number")
    @GeneratedValue
    long number;


    List<Account> accounts;

    public Client() {

    }

    public Client(long number, String name, List<Account> accounts) {
        this.number = number;
        this.name = name;
        this.accounts = accounts;
    }
    //геттеры
    public UUID getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    //сеттеры

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}