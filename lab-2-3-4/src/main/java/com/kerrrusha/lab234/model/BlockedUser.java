package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public int getBlockedUserId() {
        return blocked_user_id;
    }

    public int getBlockedByUserId() {
        return blocked_by_user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedUser that = (BlockedUser) o;
        return id == that.id && blocked_user_id == that.blocked_user_id && blocked_by_user_id == that.blocked_by_user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blocked_user_id, blocked_by_user_id);
    }
}
