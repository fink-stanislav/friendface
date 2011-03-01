package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 12:00 PM
 */

public interface UserDAO {
    void createUser(User user) throws Exception;
    void deleteUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    User getUser(Integer userId) throws Exception;
    User getUser(String loginEmail) throws Exception;
    Boolean isUserExists(User user) throws Exception;
}
