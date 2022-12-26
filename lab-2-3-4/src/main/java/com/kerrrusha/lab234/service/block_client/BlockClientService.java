package com.kerrrusha.lab234.service.block_client;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.blocked_user.BlockedUserDao;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.exception.BlockClientException;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.block_client.result.BlockClientResult;
import com.kerrrusha.lab234.validator.BlockUserValidator;
import org.apache.http.HttpStatus;

import java.util.Collection;

import static java.util.Collections.singletonList;

public class BlockClientService {

    private final BlockedUserDao blockedUserDao;
    private final UserDao userDao;
    private final User user;

    public BlockClientService(User user) throws DBException {
        blockedUserDao = new BlockedUserDao();
        userDao = new UserDao();
        this.user = user;
    }

    public BlockClientResult blockClientAsAdmin(String clientIdStr) {
        BlockClientResult result = new BlockClientResult();
        User blockedUser;
        try {
            int clientId = Integer.parseInt(clientIdStr);
            BlockUserValidator validator = new BlockUserValidator(user, clientId);
            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty() && !validator.onlyErrorRestrictedUser()) {
                throw new BlockClientException(errorPool);
            }
            blockedUserDao.insert(clientId, user.getId());
            blockedUser = userDao.findOneById(clientId);
        } catch (BlockClientException | NumberFormatException | DBException e) {
            result.setStatus(HttpStatus.SC_CONFLICT);
            result.setErrorPool(singletonList(e.getMessage()));
            return result;
        }
        result.setStatus(HttpStatus.SC_OK);
        result.setClient(blockedUser);
        return result;
    }

    public BlockClientResult unblockClientAsAdmin(String clientIdStr) {
        BlockClientResult result = new BlockClientResult();
        User blockedUser;
        try {
            int clientId = Integer.parseInt(clientIdStr);
            BlockUserValidator validator = new BlockUserValidator(user, clientId);
            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty() && !validator.onlyErrorRestrictedUser()) {
                throw new BlockClientException(errorPool);
            }
            blockedUserDao.delete(clientId);
            blockedUser = userDao.findOneById(clientId);
        } catch (BlockClientException | NumberFormatException | DBException e) {
            result.setStatus(HttpStatus.SC_CONFLICT);
            result.setErrorPool(singletonList(e.getMessage()));
            return result;
        }
        result.setStatus(HttpStatus.SC_OK);
        result.setClient(blockedUser);
        return result;
    }
}
