package net.friendface.friendface.model.dao.user;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.DefaultQueryParams;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Author: S. Fink
 * Date: 31.01.11
 * Time: 23:25
 */

public class UserDAOImpl extends EntityDAO implements UserDAO {
    public UserDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    public void insertUser(User user) {
        perform(new Operation<User>(user) {
            @Override
            public void perform() throws RepositoryException {
                repositoryManager.setupRepository(entity);
                entityManager.persist(entity);
            }
        });
    }

    public void deleteUser(User user) {
        perform(new Operation<User>(user) {
            @Override
            public void perform() throws RepositoryException {
                User user = entity;
                entityManager.remove(entity);
                repositoryManager.removeRepository(user);
            }
        });
    }

    public void updateUser(User user) {
        perform(new Operation<User>(user) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.merge(entity);
            }
        });
    }

    public User getById(Integer userId) {
        return getById(userId, User.class);
    }

    public User getUser(String loginEmail) {
        try {
            DefaultQueryParams<String> queryParams = new DefaultQueryParams<String>("getUserByLogin");
            queryParams.setParam("loginEmail", loginEmail);
            return queryExecutor.executeNamedQuery(queryParams, User.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Boolean isUserExists(User user) {
        return getUser(user.getLoginEmail()) != null;
    }
}
