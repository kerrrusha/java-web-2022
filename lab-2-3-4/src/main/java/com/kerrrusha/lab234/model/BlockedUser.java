package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;

public class BlockedUser {

    private int id;
    private int blocked_user_id;
    private int blocked_by_user_id;
    private LocalDateTime created_time;
}
