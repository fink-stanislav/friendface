package com.exadel.friendface.service.user;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.view.beans.LoginBean;
import com.exadel.friendface.view.beans.RegistrationBean;

import java.util.Map;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;
import static com.exadel.friendface.service.user.UserUtils.*;

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

    public boolean login(LoginBean bean, Map session) throws Exception {
        User userFromRequest = getUserFromBean(bean);
        User userFromStorage = find(userFromRequest.getLoginEmail());
        if (checkCredentials(userFromRequest, userFromStorage)) {
            session.put(getUserSessionKey(), userFromStorage);
            return true;
        }
        return false;
    }

    public boolean register(RegistrationBean bean) throws Exception {
        User user = getUserFromBean(bean);
        UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();

        if (!userDAO.isUserExists(user)) {
            userDAO.createUser(user);
            // send mail
            return true;
        }
        return false;
    }

    public void logout(Map session) throws Exception {
        session.put(getUserSessionKey(), null);
    }

    public User getFromSession(Map session) {
        return (User) session.get(getUserSessionKey());
    }

    public void removeUser(User user) {
    }

    public void updateUser(User user) {
    }

    public User find(String login) throws Exception {
        return getDAOFactory().getUserDAO().getUser(login);
    }

    public User find(Integer id) throws Exception {
        return getDAOFactory().getUserDAO().getUser(id);
    }
}
