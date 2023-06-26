package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    protected UUID id;

    @Column(name = "client_id")
    protected UUID clientID;
    @Column(name = "amount")
    protected int amount;
    @Column(name = "number")
    protected long number;

    public Account() {

    }

    public Account(UUID clientID, long number, int amount) {
        this.clientID = clientID;
        this.number = number;
        this.amount = amount;
    }

    //геттеры

    public UUID getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }

    public int getAmount() {
        return amount;
    }

    public UUID getClientID() {
        return clientID;
    }
    //сеттеры

    public void setNumber(long number) {
        this.number = number;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }
}
