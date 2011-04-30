package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.dao.friends.FriendsDAO;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;

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
