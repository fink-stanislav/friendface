package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

/**
 * User: sfink
 * Date: 2/2/11
 * Time: 12:00 PM
 */

public interface UserDAO {
    int createUser(User user);
    int deleteUser(User user);
    int updateUser(User user);
    User getUser(Integer userId);
    User getUser(String loginEmail);
    boolean checkCredentials(User user);
    Boolean isUserExists(User user);
}
