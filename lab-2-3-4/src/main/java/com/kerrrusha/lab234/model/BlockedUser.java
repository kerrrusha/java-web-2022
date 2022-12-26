package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;

public class BlockedUser {

    private int id;
    private int blocked_user_id;
    private int blocked_by_user_id;
    private LocalDateTime created_time;

    public void setId(int id) {
        this.id = id;
    }

    public void setBlockedUserId(int blocked_user_id) {
        this.blocked_user_id = blocked_user_id;
    }

    public void setBlockedByUserId(int blocked_by_user_id) {
        this.blocked_by_user_id = blocked_by_user_id;
    }

    public void setCreatedTime(LocalDateTime created_time) {
        this.created_time = created_time;
    }
}
