package com.exadel.friendface.service.user;

import com.exadel.friendface.controllers.actions.helpers.SessionHelper;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.view.beans.LoginBean;
import com.exadel.friendface.view.beans.RegistrationBean;

import java.util.List;
import java.util.Map;

import static com.exadel.friendface.service.user.UserUtils.*;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:42 AM
 */

public class UserService {
    private static UserService service;
    private UserDAO dao;

    public static UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    private UserService() {
        try {
            dao = DAOFactory.getDAOFactory().getUserDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(LoginBean bean, SessionHelper session) {
        User userFromRequest = getUserFromBean(bean);
        User userFromStorage = getByLogin(userFromRequest.getLoginEmail());
        if (checkCredentials(userFromRequest, userFromStorage)) {
            session.putToSession(getUserSessionKey(), userFromStorage);
            return true;
        }
        return false;
    }

    public boolean register(RegistrationBean bean) {
        User user = getUserFromBean(bean);
        if (!dao.isUserExists(user)) {
            dao.createUser(user);
            // send mail
            return true;
        }
        return false;
    }

    public void logout(SessionHelper session) {
        session.putToSession(getUserSessionKey(), null);
    }

    public User getFromSession(SessionHelper session) {
        return (User) session.getFromSession(getUserSessionKey());
    }

    public void removeUser(User user) {
    }

    public void updateUser(User user) {
    }

    public User getByLogin(String login) {
        return dao.getUser(login);
    }

    public User getById(Integer id) {
        return dao.getUser(id);
    }

    public List<User> find(Map<String, String> searchParams) throws Exception {
        return dao.findUsers(searchParams);
    }
}
