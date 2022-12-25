package com.kerrrusha.lab234.service.moneycard.result.open_new_card;

import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.service.AbstractResult;

public class OpenMoneyCardResult extends AbstractResult {

	private MoneyCard moneyCard;

	public MoneyCard getMoneyCard() {
		return moneyCard;
	}

	public void setMoneyCard(MoneyCard moneyCard) {
		this.moneyCard = moneyCard;
	}
}
