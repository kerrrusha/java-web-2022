package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;

public class Billing {

    private int id;
    private int money_amount;
    private int from_money_card_id;
    private int to_money_card_id;
    private int billing_status_id;
    private LocalDateTime created_time;
    private LocalDateTime updated_time;
}
