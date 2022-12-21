package com.kerrrusha.lab234.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.kerrrusha.lab234.factory.MoneyCardNumberFactory.createNumber;

public class MoneyCard implements Serializable {

    private int id;
    private int money_account_id;
    private int balance;
    private String number;
    private String secret;
    private LocalDate expiration_date;

    private LocalDateTime created_time;
    private LocalDateTime updated_time;

    public MoneyCard() {}

    public MoneyCard(MoneyCard other) {
        this.id = other.getId();
        this.money_account_id = other.getMoneyAccountId();
        this.balance = other.getBalance();
        this.number = other.getNumber();
        this.secret = other.getSecret();
        this.expiration_date = other.getExpirationDate();
        this.created_time = other.getCreatedTime();
        this.updated_time = other.getUpdatedTime();
    }

    public boolean isActive() {
        return expiration_date.isAfter(LocalDateTime.now().toLocalDate());
    }

    public LocalDateTime getCreatedTime() {
        return created_time;
    }

    public LocalDateTime getUpdatedTime() {
        return updated_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoneyAccountId() {
        return money_account_id;
    }

    public int getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public String getSecret() {
        return secret;
    }

    public LocalDate getExpirationDate() {
        return expiration_date;
    }

    public void setMoneyAccountId(int money_account_id) {
        this.money_account_id = money_account_id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpirationDate(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setCreatedTime(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    public void setUpdatedTime(LocalDateTime updated_time) {
        this.updated_time = updated_time;
    }

    public MoneyCard createWithNumberFromId() {
        if (getId() == 0) {
            return null;
        }
        String cardNumber = createNumber(getId());
        setNumber(cardNumber);
        return new MoneyCard(this);
    }
}
