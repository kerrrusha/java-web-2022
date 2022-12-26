package com.kerrrusha.lab234.service.block_money_account.result;

import com.kerrrusha.lab234.model.MoneyAccount;
import com.kerrrusha.lab234.service.AbstractResult;

public class BlockMoneyAccountResult extends AbstractResult {

    private MoneyAccount moneyAccount;

    public MoneyAccount getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(MoneyAccount moneyAccount) {
        this.moneyAccount = moneyAccount;
    }
}
