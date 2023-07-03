package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private long number;
    @OneToMany(mappedBy = "client_id", fetch = FetchType.LAZY)
    private List<Account> accounts;

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
    public void setId(UUID id) {
        this.id = id;
    }

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
