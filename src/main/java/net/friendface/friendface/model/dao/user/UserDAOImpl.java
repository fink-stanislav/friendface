package net.friendface.friendface.model.dao.user;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.search.JpaSearch;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 31.01.11
 * Time: 23:25
 */

public class UserDAOImpl extends EntityDAO implements UserDAO {
    public UserDAOImpl(EntityManagerProvider provider) {
        super(provider);
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
        return getQueryExecutor().
                executeNamedQuery("getUserByLogin", User.class, "loginEmail", loginEmail);
    }

    public List<User> findUsers(Map<String, String> searchParams) {
        JpaSearch search = new JpaSearch(getEntityManager());
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
