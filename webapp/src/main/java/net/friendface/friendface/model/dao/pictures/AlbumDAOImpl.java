package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.DefaultQueryParams;

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

    public void insertAlbum(Album album) {
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

    public void deleteAlbum(Album album) {
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
        perform(new Operation<Album>(album) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.merge(entity);
            }
        });
    }

    public List<Album> getUserAlbums(User user) {
        try {
            DefaultQueryParams queryParams = new DefaultQueryParams("getAlbumsByUser");
            queryParams.setParam("user", user);
            return queryExecutor.executeNamedQueryList(queryParams, Album.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Album getById(Integer id) {
        return getById(id, Album.class);
    }

    public Long getPictureCount(Album album) {
        try {
            DefaultQueryParams queryParams = new DefaultQueryParams("getPicturesCount");
            queryParams.setParam("album", album);
            return queryExecutor.executeCountQuery(queryParams, Long.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Integer getFrontPictureId(Album album) {
        return 0;
    }

    public String getPath(Identifiable album) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Album) album).getUser().getLoginEmail())
                .append("/albums");
        return sb.toString();
    }
}
