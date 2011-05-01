package net.friendface.friendface.model.dao.user;

import net.friendface.friendface.model.entities.User;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:00 PM
 */

public interface UserDAO {
    void createUser(User user) throws RepositoryException;

    void deleteUser(User user) throws RepositoryException;

    void updateUser(User user);

    User getUser(Integer userId);

    User getUser(String loginEmail);

    List<User> findUsers(Map<String, String> searchParams);

    Boolean isUserExists(User user);
}
