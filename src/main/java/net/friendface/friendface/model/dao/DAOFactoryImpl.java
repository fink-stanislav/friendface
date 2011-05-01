package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.dao.friends.FriendsDAO;
import net.friendface.friendface.model.dao.friends.FriendsDAOImpl;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.dao.user.UserDAOImpl;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAOImpl;
import net.friendface.friendface.model.providers.EntityManagerProvider;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 4/19/11
 * Time: 9:01 PM
 */

public class DAOFactoryImpl extends DAOFactory {
    private EntityManagerProvider provider;

    public DAOFactoryImpl(EntityManagerProvider provider) {
        this.provider = provider;
    }

    @Override
    public UserDAO getUserDAO() throws RepositoryException {
        return new UserDAOImpl(provider);
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        return new FriendsDAOImpl(provider);
    }

    @Override
    public WallMessageDAO getMessageDAO() throws RepositoryException {
        return new WallMessageDAOImpl(provider);
    }
}
