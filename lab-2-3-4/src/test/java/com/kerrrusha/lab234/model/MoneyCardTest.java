package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyCardTest {

    @Test
    public void setNumberFromIdTest() {
        final int id = 4143;
        final String expected = "0000 0000 0000 4143";

        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setId(id);
        moneyCard.setNumberFromId();

        assertEquals(expected, moneyCard.getNumber());
    }

    @Test
    public void setNumberFromIdTest1() {
        final int id = 0;
        final String expected = "0000 0000 0000 0000";

        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setId(id);
        moneyCard.setNumberFromId();

        assertEquals(expected, moneyCard.getNumber());
    }

    @Test
    public void setNumberFromIdTest2() {
        final int id = 12345;
        final String expected = "0000 0000 0001 2345";

        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setId(id);
        moneyCard.setNumberFromId();

        assertEquals(expected, moneyCard.getNumber());
    }

    @Test
    public void getPrettyBalanceStringTest() {
        final int balance = 1234;
        final String expected = "12.34 $";

        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setBalance(balance);

        assertEquals(expected, moneyCard.getPrettyBalanceString());
    }

    @Test
    public void takeMoneyTest() {
        final int startBalance = 1234;
        final int delta = 34;
        final String expected = "12.00 $";

        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setBalance(startBalance);
        moneyCard.takeMoney(delta);

        assertEquals(expected, moneyCard.getPrettyBalanceString());
    }

    @Test
    public void giveMoneyTest() {
        final int startBalance = 1234;
        final int delta = 66;
        final String expected = "13.00 $";

        MoneyCard moneyCard = new MoneyCard();

        moneyCard.setBalance(startBalance);
        moneyCard.giveMoney(delta);

        assertEquals(expected, moneyCard.getPrettyBalanceString());
    }
}
