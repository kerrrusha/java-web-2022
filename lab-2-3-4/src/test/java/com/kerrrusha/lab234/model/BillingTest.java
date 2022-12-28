package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BillingTest {

    @Test
    public void isEqualTest() {
        assertEquals(getExampleBilling1(), getExampleBilling1());
    }

    @Test
    public void isEqual1Test() {
        assertEquals(getExampleBilling2(), getExampleBilling2());
    }

    @Test
    public void isNotEqualTest() {
        assertNotEquals(getExampleBilling1(), getExampleBilling2());
    }

    private Billing getExampleBilling1() {
        Billing billing = new Billing();

        billing.setId(1);
        billing.setMoneyAmount(100);

        return billing;
    }

    private Billing getExampleBilling2() {
        Billing billing = new Billing();

        billing.setId(2);
        billing.setMoneyAmount(200);

        return billing;
    }
}
