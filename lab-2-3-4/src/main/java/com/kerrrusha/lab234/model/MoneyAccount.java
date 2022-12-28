package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MoneyAccount {

    private int id;
    private String name;
    private int owner_user_id;
    private LocalDateTime created_time;
    private LocalDateTime updated_time;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerUserId(int owner_user_id) {
        this.owner_user_id = owner_user_id;
    }

    public void setCreatedTime(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    public void setUpdatedTime(LocalDateTime updated_time) {
        this.updated_time = updated_time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerUserId() {
        return owner_user_id;
    }

    public LocalDateTime getCreatedTime() {
        return created_time;
    }

    public LocalDateTime getUpdatedTime() {
        return updated_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyAccount that = (MoneyAccount) o;
        return getId() == that.getId() && owner_user_id == that.owner_user_id && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), owner_user_id);
    }
}
