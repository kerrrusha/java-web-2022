package com.kerrrusha.lab234.viewmodel;

import java.time.LocalDateTime;
import java.util.Objects;

public class BillingViewModel {

    private int billingId;
    private int moneyAmount;
    private String fromCardNumber;
    private String toCardNumber;
    private String statusName;
    private LocalDateTime createdTime;

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setFromCardNumber(String fromCardNumber) {
        this.fromCardNumber = fromCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingViewModel that = (BillingViewModel) o;
        return billingId == that.billingId && moneyAmount == that.moneyAmount && Objects.equals(fromCardNumber, that.fromCardNumber) && Objects.equals(toCardNumber, that.toCardNumber) && Objects.equals(statusName, that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billingId, moneyAmount, fromCardNumber, toCardNumber, statusName);
    }
}
