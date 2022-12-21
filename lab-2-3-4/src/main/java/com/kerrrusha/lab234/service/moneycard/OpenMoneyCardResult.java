package com.kerrrusha.lab234.service.moneycard;

import com.kerrrusha.lab234.model.MoneyCard;

import java.util.Collection;

public class OpenMoneyCardResult {

	private Collection<String> errorPool;
	private int status;
	private MoneyCard moneyCard;

	public Collection<String> getErrorPool() {
		return errorPool;
	}

	public void setErrorPool(Collection<String> errorPool) {
		this.errorPool = errorPool;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MoneyCard getMoneyCard() {
		return moneyCard;
	}

	public void setMoneyCard(MoneyCard moneyCard) {
		this.moneyCard = moneyCard;
	}
}
