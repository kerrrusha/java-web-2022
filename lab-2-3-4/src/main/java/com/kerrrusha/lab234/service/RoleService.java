package com.kerrrusha.lab234.service;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.role.RoleDao;
import com.kerrrusha.lab234.model.User;

public class RoleService {

    private static final String ADMIN_NAME = "admin";

    private final RoleDao roleDao;

    public RoleService() throws DBException {
        roleDao = new RoleDao();
    }

    public String getRoleNameById(int roleId) throws DBException {
        return roleDao.findOneById(roleId).getName();
    }

    public boolean userIsAdmin(User user) throws DBException {
        return roleDao.findOneById(user.getRoleId()).getName().equalsIgnoreCase(ADMIN_NAME);
    }
}
