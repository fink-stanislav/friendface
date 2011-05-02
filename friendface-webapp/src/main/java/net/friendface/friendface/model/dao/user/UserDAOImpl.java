package net.friendface.friendface.model.dao.user;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.search.JpaSearch;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 31.01.11
 * Time: 23:25
 */

public class UserDAOImpl extends EntityDAO implements UserDAO {
    public UserDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    public void createUser(User user) throws RepositoryException {
        repositoryManager.setupRepository(user);
        persistEntity(user);
    }

    public void deleteUser(User user) throws RepositoryException {
        repositoryManager.removeRepository(user);
        removeEntity(user);
    }

    public void updateUser(User user) {
        updateEntity(user);
    }

    public User getUser(Integer userId) {
        return getById(userId, User.class);
    }

    public User getUser(String loginEmail) {
        return queryExecutor.executeNamedQuery("getUserByLogin", User.class, "loginEmail", loginEmail);
    }

    public List<User> findUsers(Map<String, String> searchParams) {
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
