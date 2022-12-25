package com.kerrrusha.lab234.viewmodel;

import java.time.LocalDateTime;

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
}
