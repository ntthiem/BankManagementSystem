package com.example.bankmanagementsystem.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "bank_id")
    private Long bank_id;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "bank_id", insertable = false, updatable = false)
    private Bank bank;

    @OneToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    public Account() {}

    public Account(String number, Long customer_id, Long bank_id, Float balance, String status) {
        this.number = number;
        this.customer_id = customer_id;
        this.bank_id = bank_id;
        this.balance = balance;
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getBankId() {
        return bank_id;
    }

    public void setBankId(Long bank_id) { this.bank_id = bank_id; }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
