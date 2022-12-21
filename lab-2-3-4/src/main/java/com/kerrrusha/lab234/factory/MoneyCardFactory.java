package com.kerrrusha.lab234.factory;

import com.kerrrusha.lab234.model.MoneyCard;

import java.time.LocalDate;

import static com.kerrrusha.lab234.util.RandomUtil.generateRandomNDigits;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class MoneyCardFactory {

    private static final int SECRET_DIGITS_AMOUNT = 3;
    private static final int CARD_LIFE_YEARS = 4;

    public static MoneyCard createNewMoneyCard(int moneyAccountId) {
        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setSecret(generateRandomNDigits(SECRET_DIGITS_AMOUNT));
        moneyCard.setMoneyAccountId(moneyAccountId);
        moneyCard.setBalance(0);
        moneyCard.setNumber(EMPTY);
        moneyCard.setExpirationDate(LocalDate.now().plusYears(CARD_LIFE_YEARS));

        return moneyCard;
    }
}
