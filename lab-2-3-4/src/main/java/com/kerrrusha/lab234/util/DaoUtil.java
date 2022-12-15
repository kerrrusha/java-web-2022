package com.kerrrusha.lab234.util;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;

public class DaoUtil {

	public static int getUserId(String phone) {
		try {
			return new UserDao().findOneByPhone(phone).getId();
		} catch (DBException e) {
			throw new RuntimeException(e);
		}
	}
}
