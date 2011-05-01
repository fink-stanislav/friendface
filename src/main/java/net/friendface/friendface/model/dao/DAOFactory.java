package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.dao.friends.FriendsDAO;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.providers.EntityManagerProvider;

import javax.jcr.RepositoryException;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {
    private EntityManagerProvider provider;
    public abstract UserDAO getUserDAO() throws RepositoryException;

    public abstract FriendsDAO getFriendsDAO();

    public abstract WallMessageDAO getMessageDAO() throws RepositoryException;

    public static DAOFactory getDAOFactory() {
        return new DAOFactoryImpl(EntityManagerProvider.getInstance());
    }
}
