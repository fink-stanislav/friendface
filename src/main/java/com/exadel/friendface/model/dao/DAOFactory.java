package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.dao.friends.FriendsDAO;
import com.exadel.friendface.model.dao.user.UserDAO;
import com.exadel.friendface.model.dao.wallmessage.WallMessageDAO;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {
    public abstract UserDAO getUserDAO();
    public abstract FriendsDAO getFriendsDAO();
    public abstract WallMessageDAO getMessageDAO();

    public static DAOFactory getDAOFactory() throws Exception {
        return new DAOFactoryImpl();
    }
}
