package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
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

    public void insertAlbum(Album album) throws RepositoryException {
        perform(new Operation<Album>(album) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
                Node root = repositoryManager.getRootNode();
                Node albumNode = repositoryManager.getNode(root, getPath(entity));
                repositoryManager.addNode(albumNode, Integer.toString(entity.getId()));
            }
        });
    }

    public void deleteAlbum(Album album) throws RepositoryException {
        perform(new Operation<Album>(album) {
            @Override
            public void perform() throws RepositoryException {
                repositoryManager.removeNode(
                        "/" + getPath(entity) + "/" + Integer.toString(entity.getId())
                );
                entityManager.remove(entity);
            }
        });
    }

    public void updateAlbum(Album album) {
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
        try {
            return (Integer) queryExecutor.executeCountQuery("getPicturesCount", "album", album);
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getPath(Identifiable album) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Album) album).getUser().getLoginEmail())
                .append("/albums");
        return sb.toString();
    }
}
