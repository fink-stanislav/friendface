package com.exadel.friendface.service.user;

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

    public boolean login(LoginBean bean, Map session) throws Exception {
        User userFromRequest = getUserFromBean(bean);
        User userFromStorage = getByLogin(userFromRequest.getLoginEmail());
        if (checkCredentials(userFromRequest, userFromStorage)) {
            session.put(getUserSessionKey(), userFromStorage);
            return true;
        }
        return false;
    }

    public boolean register(RegistrationBean bean) throws Exception {
        User user = getUserFromBean(bean);

        if (!dao.isUserExists(user)) {
            dao.createUser(user);
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

    public User getByLogin(String login) throws Exception {
        return dao.getUser(login);
    }

    public User getById(Integer id) throws Exception {
        return dao.getUser(id);
    }

    public List<User> find(Map<String, String> searchParams) throws Exception {
        return dao.findUsers(searchParams);
    }
}
