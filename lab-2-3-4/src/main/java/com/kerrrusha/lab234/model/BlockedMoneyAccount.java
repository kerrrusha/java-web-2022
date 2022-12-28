package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class BlockedMoneyAccount {

    private int id;
    private int blocked_money_account_id;
    private int blocked_by_user_id;
    private LocalDateTime created_time;

    public void setId(int id) {
        this.id = id;
    }

    public void setBlockedMoneyAccountId(int blocked_money_account_id) {
        this.blocked_money_account_id = blocked_money_account_id;
    }

    public int getBlockedMoneyAccountId() {
        return blocked_money_account_id;
    }

    public int getBlockedByUserId() {
        return blocked_by_user_id;
    }

    public void setBlockedByUserId(int blocked_by_user_id) {
        this.blocked_by_user_id = blocked_by_user_id;
    }

    public void setCreatedTime(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedMoneyAccount that = (BlockedMoneyAccount) o;
        return id == that.id && blocked_money_account_id == that.blocked_money_account_id && blocked_by_user_id == that.blocked_by_user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blocked_money_account_id, blocked_by_user_id);
    }
}
