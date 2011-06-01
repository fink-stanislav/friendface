package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.dao.friends.FriendDAOImpl;
import net.friendface.friendface.model.dao.pictures.AlbumDAO;
import net.friendface.friendface.model.dao.pictures.AlbumDAOImpl;
import net.friendface.friendface.model.dao.pictures.PictureDAO;
import net.friendface.friendface.model.dao.pictures.PictureDAOImpl;
import net.friendface.friendface.model.dao.user.UserDAO;
import net.friendface.friendface.model.dao.user.UserDAOImpl;
import net.friendface.friendface.model.dao.videos.VideoDAO;
import net.friendface.friendface.model.dao.videos.VideoDAOImpl;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAOImpl;
import net.friendface.friendface.model.providers.RepositoryManager;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;

/**
 * Author: S. Fink
 * Date: 4/19/11
 * Time: 9:01 PM
 */

public class DAOFactoryImpl extends DAOFactory {
    private EntityManager entityManager;
    private RepositoryManager repositoryManager;

    public DAOFactoryImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        this.entityManager = entityManager;
        this.repositoryManager = repositoryManager;
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl(entityManager, repositoryManager);
    }

    @Override
    public FriendDAO getFriendsDAO() {
        return new FriendDAOImpl(entityManager, repositoryManager);
    }

    @Override
    public WallMessageDAO getMessageDAO() {
        return new WallMessageDAOImpl(entityManager, repositoryManager);
    }

    @Override
    public PictureDAO getPictureDAO() {
        return new PictureDAOImpl(entityManager, repositoryManager);
    }

    @Override
    public AlbumDAO getAlbumDAO() {
        return new AlbumDAOImpl(entityManager, repositoryManager);
    }

    @Override
    public VideoDAO getVideoDAO() {
        return new VideoDAOImpl(entityManager, repositoryManager);
    }
}
