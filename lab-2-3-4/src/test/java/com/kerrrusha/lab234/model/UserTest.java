package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    @Test
    public void equalsTest() {
        User user1 = getUserExample1();
        User user2 = getUserExample1();

        assertEquals(user1, user2);
    }

    @Test
    public void equalsTest1() {
        User user1 = getUserExample2();
        User user2 = getUserExample2();

        assertEquals(user1, user2);
    }

    @Test
    public void notEqualsTest() {
        User user1 = getUserExample1();
        User user2 = getUserExample2();

        assertNotEquals(user1, user2);
    }

    private User getUserExample1() {
        User user = new User();

        user.setRoleId(1);
        user.setFirstName("Kirill");
        user.setLastName("Koval");
        user.setPhone("123-456");
        user.setPassword("123456");
        user.setId(1);

        return user;
    }

    private User getUserExample2() {
        User user = new User();

        user.setRoleId(1);
        user.setFirstName("James");
        user.setLastName("Hetfield");
        user.setPhone("534-257");
        user.setPassword("123456");
        user.setId(2);

        return user;
    }
}
