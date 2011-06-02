package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.DefaultQueryParams;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 9:51 PM
 */

public class PictureDAOImpl extends EntityDAO implements PictureDAO {
    public PictureDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    public void insertPicture(Picture picture) {
        perform(new Operation<Picture>(picture) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
                repositoryManager.storeContent(entity, getPath(entity.getAlbum()));
            }
        });
    }

    public void deletePicture(Picture picture) {
        perform(new Operation<Picture>(picture) {
            @Override
            public void perform() throws RepositoryException {
                repositoryManager.removeContent(entity, getPath(entity.getAlbum()));
                entityManager.remove(entity);
            }
        });
    }

    public void updatePicture(Picture picture) {
        perform(new Operation<Picture>(picture) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.merge(entity);
            }
        });
    }

    @Override
    public void deleteAlbumPictures(Album album) {
        perform(new Operation<Album>(album) {
            @Override
            public void perform() throws RepositoryException {
                DefaultQueryParams<Album> queryParams = new DefaultQueryParams<Album>("deleteAlbumPictures");
                queryParams.setParam("album", entity);
                queryExecutor.executeUpdateQuery(queryParams);
            }
        });
    }

    public List<Picture> getPictures(Album album) {
        try {
            DefaultQueryParams<Album> queryParams = new DefaultQueryParams<Album>("getPicturesByAlbum");
            queryParams.setParam("album", album);
            return queryExecutor.executeNamedQueryList(queryParams, Picture.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Picture getById(Integer id) throws RepositoryException {
        try {
            Picture picture = getById(id, Picture.class);
            return repositoryManager.retrieveContent(picture, getPath(picture.getAlbum()));
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getPath(Identifiable album) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Album) album).getUser().getLoginEmail())
                .append("/albums/")
                .append(Integer.toString(album.getId()));
        return sb.toString();
    }
}
