package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

/**
 * User: sfink
 * Date: 2/4/11
 * Time: 4:31 PM
 */

public interface AuthorizationDAO {
    public Boolean isUserLoggedIn(User user) throws Exception;
    public int loginUser(User user) throws Exception;
    public int logoutUser(User user) throws Exception;
}
