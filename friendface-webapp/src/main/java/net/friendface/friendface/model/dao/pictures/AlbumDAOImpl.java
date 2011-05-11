package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.providers.RepositoryManager;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 5/6/11
 * Time: 9:29 PM
 */

public class AlbumDAOImpl extends EntityDAO implements AlbumDAO {
    public AlbumDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    public List<Album> getUserAlbums(User user) {
        try {
            return queryExecutor.executeNamedQueryList("getAlbumsByUser", Album.class, "user", user);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Album getById(Integer id) {
        return getById(id, Album.class);
    }

    public Integer getPictureCount(Album album) {
        return (Integer) queryExecutor.executeCountQuery("getPicturesCount", "album", album);
    }

    public void insertAlbum(Album album) throws RepositoryException {
        persistEntity(album);
        Node root = repositoryManager.getRootNode();
        Node albumNode = repositoryManager.getNode(root, getPath(album));
        repositoryManager.addNode(albumNode, Integer.toString(album.getId()));
    }

    public void deleteAlbum(Album album) throws RepositoryException {
        repositoryManager.removeNode(
                "/" + getPath(album) + "/" + Integer.toString(album.getId())
        );
        removeEntity(album);
    }

    public void updateAlbum(Album album) {
    }

    public String getPath(Identifiable album) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Album) album).getUser().getLoginEmail())
                .append("/albums");
        return sb.toString();
    }
}
