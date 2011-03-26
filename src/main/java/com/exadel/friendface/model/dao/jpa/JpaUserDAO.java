package com.exadel.friendface.model.dao.jpa;

import com.exadel.friendface.model.dao.UserDAO;
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

public class JpaUserDAO extends JpaDAO implements UserDAO {
    public JpaUserDAO() {
        super();
    }

    public void createUser(User user) {
        persistEntity(user);
    }

    public void deleteUser(User user) {
        removeEntity(user);
    }

    public void updateUser(User user) {
        updateEntity(user);
    }

    public User getUser(Integer userId) {
        return getById(userId, User.class);
    }

    public User getUser(String loginEmail) {
        return executeNamedQuery("getUserByLogin", User.class, "loginEmail", loginEmail);
    }

    public List<User> findUsers(Map<String, String> searchParams) throws Exception {
        JpaSearch search = new JpaSearch(entityManager);
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
