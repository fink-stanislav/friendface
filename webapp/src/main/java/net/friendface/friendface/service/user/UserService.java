package net.friendface.friendface.service.user;

import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.view.beans.LoginBean;
import net.friendface.friendface.view.beans.RegistrationBean;
import org.apache.jackrabbit.value.BinaryImpl;

import javax.jcr.RepositoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static net.friendface.friendface.model.dao.DAOFactory.getDAOFactory;
import static net.friendface.friendface.service.user.UserUtils.checkCredentials;
import static net.friendface.friendface.service.user.UserUtils.getUserFromBean;

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
        dao = getDAOFactory().getUserDAO();
    }

    public User login(LoginBean bean) {
        User userFromRequest = getUserFromBean(bean);
        User userFromStorage = getByLogin(userFromRequest.getLoginEmail());
        if (userFromStorage != null && checkCredentials(userFromRequest, userFromStorage)) {
            return userFromStorage;
        } else {
            return null;
        }
    }

    public boolean register(RegistrationBean bean) {
        User user = getUserFromBean(bean);
        if (!dao.isUserExists(user)) {
            dao.insertUser(user);
            return true;
        }
        return false;
    }

    public void removeUser(User user) {
        dao.deleteUser(user);
    }

    public void addUserpic(User user, File file) throws IOException, RepositoryException {
        user.setContent(new BinaryImpl(new FileInputStream(file)));
        dao.addUserpic(user);
    }

    public void removeUserpic(User user) throws RepositoryException {
        dao.removeUserpic(user);
    }

    public void renameUser(User user) {
        User u = getById(user.getId());
        u.setUsername(user.getUsername());
        u.setUserSurname(user.getUserSurname());
        dao.updateUser(u);
    }

    public User getByLogin(String login) {
        return dao.getUser(login);
    }

    public User getById(Integer id) {
        return dao.getById(id);
    }
}
