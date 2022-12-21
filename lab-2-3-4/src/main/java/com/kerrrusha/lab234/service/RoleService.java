package com.kerrrusha.lab234.service;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.role.RoleDao;

public class RoleService {

    private final RoleDao roleDao;

    public RoleService() throws DBException {
        roleDao = new RoleDao();
    }

    public String getRoleNameById(int roleId) throws DBException {
        return roleDao.findOneById(roleId).getName();
    }
}
