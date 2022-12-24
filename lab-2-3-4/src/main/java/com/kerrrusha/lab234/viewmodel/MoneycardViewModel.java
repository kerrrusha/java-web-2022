package com.kerrrusha.lab234.viewmodel;

import java.time.LocalDate;

public class MoneycardViewModel {

    private int moneyAccountId;
    private String moneyAccountName;
    private String moneyCardNumber;
    private int balance;
    private String secret;
    private LocalDate expirationDate;
    private LocalDate openedDate;

    public void setMoneyAccountId(int moneyAccountId) {
        this.moneyAccountId = moneyAccountId;
    }

    public void setMoneyAccountName(String moneyAccountName) {
        this.moneyAccountName = moneyAccountName;
    }

    public void setMoneyCardNumber(String moneyCardNumber) {
        this.moneyCardNumber = moneyCardNumber;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
    }
}
