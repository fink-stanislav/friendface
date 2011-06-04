package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.dao.messages.PrivateMessageDAO;
import net.friendface.friendface.model.dao.messages.WallMessageDAO;
import net.friendface.friendface.model.dao.pictures.AlbumDAO;
import net.friendface.friendface.model.dao.pictures.PictureDAO;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.dao.videos.VideoDAO;
import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.providers.RepositoryManagerProvider;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {
    public abstract UserDAO getUserDAO() ;

    public abstract FriendDAO getFriendsDAO();

    public abstract WallMessageDAO getMessageDAO();

    public abstract PictureDAO getPictureDAO();

    public abstract AlbumDAO getAlbumDAO();

    public abstract VideoDAO getVideoDAO();

    public abstract PrivateMessageDAO getPrivateMessageDAO();

    public static DAOFactory getDAOFactory() {
        return new DAOFactoryImpl(
                EntityManagerProvider.getInstance().getEntityManager(),
                RepositoryManagerProvider.getInstance().getRepositoryManager()
        );
    }
}
