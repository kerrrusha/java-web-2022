package com.kerrrusha.lab234.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MoneyCard {

    private int id;
    private int money_account_id;
    private int balance;
    private String number;
    private String secret;
    private LocalDate expiration_date;
    private LocalDateTime created_time;
    private LocalDateTime updated_time;

    public void setId(int id) {
        this.id = id;
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
}
