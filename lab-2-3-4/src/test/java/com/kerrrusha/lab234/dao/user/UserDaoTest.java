package com.kerrrusha.lab234.dao.user;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.factory.UserFactory;
import com.kerrrusha.lab234.model.User;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private static final Logger logger = Logger.getLogger(UserDaoTest.class);

    private final UserDao userDao;

    public UserDaoTest() throws DBException {
        userDao = new UserDao();
    }

    @Test
    public void findAllTest() throws DBException {
        Collection<User> allUsers = userDao.findAll();
        logger.info("Collected " + allUsers.size() + " users.");
    }

    @Test
    public void findOneByIdTest() throws DBException {
        User user = userDao.findOneById(1);
        assertNotNull(user);
    }

    @Test
    public void findOneByPhoneTest() throws DBException {
        User user = userDao.findOneByPhone("+380123456789");
        assertNotNull(user);
    }

    @Test
    public void insertDeleteTest() throws DBException {
        User newUser = UserFactory.createExample();

        userDao.insert(newUser);

        User sameUserFromDb = userDao.findOneByPhone(newUser.getPhone());
        assertNotNull(sameUserFromDb);
        assertEquals(newUser, sameUserFromDb);

        userDao.deleteByPhone(sameUserFromDb.getPhone());
        assertNull(userDao.findOneByPhone(sameUserFromDb.getPhone()));
    }
}
