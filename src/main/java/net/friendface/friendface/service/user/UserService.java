package net.friendface.friendface.service.user;

import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.dao.JcrHelper;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.view.beans.LoginBean;
import net.friendface.friendface.view.beans.RegistrationBean;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

import static net.friendface.friendface.model.dao.DAOFactory.getDAOFactory;
import static net.friendface.friendface.service.user.UserUtils.*;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:42 AM
 */

public class UserService {
    private static UserService service;
    private UserDAO dao;
    private JcrHelper jcrHelper;

    public static UserService getService() throws RepositoryException {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    private UserService() throws RepositoryException {
        dao = getDAOFactory().getUserDAO();
    }

    public void setJcrHelper(JcrHelper jcrHelper) {
        this.jcrHelper = jcrHelper;
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

    public boolean register(RegistrationBean bean) throws RepositoryException {
        User user = getUserFromBean(bean);
        if (!dao.isUserExists(user)) {
            dao.createUser(user);
            jcrHelper.setupRepository(user);
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

    public void removeUser(User user) throws RepositoryException {
        dao.deleteUser(user);
        jcrHelper.removeRepository(user);
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
