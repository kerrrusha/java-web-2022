package com.kerrrusha.lab234.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String phone;
    private String password;
    private int role_id;

    private LocalDateTime created_time;
    private LocalDateTime updated_time;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
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

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return role_id;
    }

    public LocalDateTime getCreatedTime() {
        return created_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return role_id == user.role_id &&
                first_name.equals(user.first_name) &&
                Objects.equals(last_name, user.last_name) &&
                getPhone().equals(user.getPhone()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, getPhone(), getPassword(), role_id);
    }
}
