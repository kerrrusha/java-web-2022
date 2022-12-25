package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;

public class Billing {

    private int id;
    private int money_amount;
    private int from_money_card_id;
    private int to_money_card_id;
    private int billing_status_id;
    private LocalDateTime created_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return created_time;
    }

    public void setCreatedTime(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    public int getMoneyAmount() {
        return money_amount;
    }

    public int getFromMoneyCardId() {
        return from_money_card_id;
    }

    public int getToMoneyCardId() {
        return to_money_card_id;
    }

    public int getBillingStatusId() {
        return billing_status_id;
    }

    public void setMoneyAmount(int money_amount) {
        this.money_amount = money_amount;
    }

    public void setFromMoneyCardId(int from_money_card_id) {
        this.from_money_card_id = from_money_card_id;
    }

    public void setToMoneyCardId(int to_money_card_id) {
        this.to_money_card_id = to_money_card_id;
    }

    public void setBillingStatusId(int billing_status_id) {
        this.billing_status_id = billing_status_id;
    }
}
