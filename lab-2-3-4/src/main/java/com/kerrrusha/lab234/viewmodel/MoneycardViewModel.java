package com.kerrrusha.lab234.viewmodel;

import java.time.LocalDate;
import java.util.Objects;

public class MoneycardViewModel {

    private int moneyAccountId;
    private boolean isBlocked;
    private String moneyAccountName;
    private String moneyCardNumber;
    private int balance;
    private String secret;
    private LocalDate expirationDate;
    private LocalDate openedDate;

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneycardViewModel viewModel = (MoneycardViewModel) o;
        return moneyAccountId == viewModel.moneyAccountId && isBlocked == viewModel.isBlocked && balance == viewModel.balance && Objects.equals(moneyAccountName, viewModel.moneyAccountName) && Objects.equals(moneyCardNumber, viewModel.moneyCardNumber) && Objects.equals(secret, viewModel.secret) && Objects.equals(expirationDate, viewModel.expirationDate) && Objects.equals(openedDate, viewModel.openedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moneyAccountId, isBlocked, moneyAccountName, moneyCardNumber, balance, secret, expirationDate, openedDate);
    }
}
