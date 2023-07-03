package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "amount")
    private long amount;
    @Column(name = "number")
    private long number;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Account() {

    }

    public Account(long number, int amount, Client client) {
        this.number = number;
        this.amount = amount;
        this.client = client;
    }

    //геттеры
    public UUID getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }

    public long getAmount() {
        return amount;
    }

    public Client getClient() {
        return client;
    }

    //сеттеры
    public void setId(UUID id) {
        this.id = id;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
