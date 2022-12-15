package com.kerrrusha.lab234.factory;

import com.kerrrusha.lab234.model.User;

public class UserFactory {

    public static User createExample() {
        User user = new User();

        user.setFirstName("exampleFirstName");
        user.setLastName("exampleLastName");
        user.setPhone("+111111111111");
        user.setPassword("examplePassword");
        user.setRoleId(1);

        return user;
    }
}
