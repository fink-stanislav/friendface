package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.dao.pictures.AlbumDAO;
import net.friendface.friendface.model.dao.pictures.PictureDAO;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.providers.RepositoryManagerProvider;

import javax.jcr.RepositoryException;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {
    public abstract UserDAO getUserDAO() throws RepositoryException;

    public abstract FriendDAO getFriendsDAO();

    public abstract WallMessageDAO getMessageDAO() throws RepositoryException;

    public abstract PictureDAO getPictureDAO();

    public abstract AlbumDAO getAlbumDAO();

    public static DAOFactory getDAOFactory() {
        return new DAOFactoryImpl(
                EntityManagerProvider.getInstance().getEntityManager(),
                RepositoryManagerProvider.getInstance().getRepositoryManager()
        );
    }
}
