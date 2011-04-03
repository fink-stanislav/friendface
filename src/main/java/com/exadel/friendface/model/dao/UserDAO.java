package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

import java.util.List;
import java.util.Map;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:00 PM
 */

public interface UserDAO {
    void createUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUser(Integer userId);

    User getUser(String loginEmail);

    List<User> findUsers(Map<String, String> searchParams);

    Boolean isUserExists(User user);
}
