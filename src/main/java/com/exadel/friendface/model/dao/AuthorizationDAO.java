package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

/**
 * User: sfink
 * Date: 2/4/11
 * Time: 4:31 PM
 */

public interface AuthorizationDAO {
    public Boolean checkCredentials(User user);
    public Boolean isUserLoggedIn(User user);
    public int loginUser(User user);
    public int logoutUser(User user);
}
