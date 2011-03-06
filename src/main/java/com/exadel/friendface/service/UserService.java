package com.exadel.friendface.service;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.view.beans.LoginBean;

import java.util.Map;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;
import static com.exadel.friendface.model.util.UserUtils.checkCredentials;
import static com.exadel.friendface.model.util.UserUtils.getUserFromBean;
import static com.exadel.friendface.model.util.UserUtils.getUserSessionKey;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:42 AM
 */

public class UserService {
    private static UserService service;

    public static UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    public boolean loginUser(LoginBean bean, Map session) throws Exception {
        User userFromRequest = getUserFromBean(bean);
        User userFromStorage = findUser(userFromRequest);
        if (checkCredentials(userFromRequest, userFromStorage)) {
            session.put(getUserSessionKey(), userFromStorage);
            return true;
        }
        return false;
    }
    public void registerUser(User user) {}
    public void removeUser(User user) {}
    public void updateUser(User user) {}

    public User findUser(User user) throws Exception {
        return getDAOFactory().getUserDAO().getUser(user.getLoginEmail());
    }
}
