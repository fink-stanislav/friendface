package net.friendface.friendface.model.dao.user;

import net.friendface.friendface.model.entities.User;

import javax.jcr.RepositoryException;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:00 PM
 */

public interface UserDAO {
    void insertUser(User user) throws RepositoryException;

    void deleteUser(User user) throws RepositoryException;

    void updateUser(User user);

    User getById(Integer userId);

    User getUser(String loginEmail);

    Boolean isUserExists(User user);
}
