package com.exadel.friendface.model.dao.mixed;

import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.dao.jcr.JcrDAO;
import com.exadel.friendface.model.dao.jpa.JpaDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.search.JpaSearch;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 31.01.11
 * Time: 23:25
 */

public class MixedUserDAO implements UserDAO {
    private JpaDAO jpaDAO;
    private JcrDAO jcrDAO;

    public MixedUserDAO() {
        jpaDAO = new JpaDAO();
        jcrDAO = new JcrDAO();
    }

    public void createUser(User user) {
        jpaDAO.persistEntity(user);
        jcrDAO.setupRepository(user);
    }

    public void deleteUser(User user) {
        jpaDAO.removeEntity(user);
    }

    public void updateUser(User user) {
        jpaDAO.updateEntity(user);
    }

    public User getUser(Integer userId) {
        return jpaDAO.getById(userId, User.class);
    }

    public User getUser(String loginEmail) {
        return jpaDAO.executeNamedQuery("getUserByLogin", User.class, "loginEmail", loginEmail);
    }

    public List<User> findUsers(Map<String, String> searchParams) {
        JpaSearch search = new JpaSearch(jpaDAO.getEntityManager());
        return search.find(searchParams, User.class);
    }

    public Boolean isUserExists(User user) {
        try {
            getUser(user.getLoginEmail());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
