package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

import javax.persistence.NoResultException;

/**
 * Author: S. Fink
 * Date: 31.01.11
 * Time: 23:25
 */

public class JpaUserDAO extends JpaDAO implements UserDAO {
    public JpaUserDAO() {
        super();
    }

    public void createUser(User user) throws Exception {
        persistEntity(user);
    }

    public void deleteUser(User user) throws Exception {
        removeEntity(user);
    }

    public void updateUser(User user) throws Exception {
        updateEntity(user);
    }

    public User getUser(Integer userId) throws Exception {
        return executeNamedQuery("getUserById", User.class, "userId", userId);
    }

    public User getUser(String loginEmail) throws Exception {
        return executeNamedQuery("getUserByLogin", User.class, "loginEmail", loginEmail);
    }

    public Boolean isUserExists(User user) throws Exception {
        try {
            getUser(user.getLoginEmail());
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}