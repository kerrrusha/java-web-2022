package com.kerrrusha.lab234.service.money.result.billing;

import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.service.AbstractResult;

public class BillingResult extends AbstractResult {

    private MoneyCard moneyCard;

    public MoneyCard getMoneyCard() {
        return moneyCard;
    }

    public void setMoneyCard(MoneyCard moneyCard) {
        this.moneyCard = moneyCard;
    }
}
