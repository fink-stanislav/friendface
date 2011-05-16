package net.friendface.friendface.model.dao.pictures;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.providers.RepositoryManager;

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

    public void insertPicture(Picture picture) throws RepositoryException {
        perform(new Operation<Picture>(picture) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
                repositoryManager.storeContent(entity, getPath(entity.getAlbum()));
            }
        });
    }

    public void deletePicture(Picture picture) throws RepositoryException {
        perform(new Operation<Picture>(picture) {
            @Override
            public void perform() throws RepositoryException {
                repositoryManager.removeContent(entity, getPath(entity.getAlbum()));
                entityManager.remove(entity);
            }
        });
    }

    public List<Picture> getPictures(Album album) throws RepositoryException {
        try {
            return queryExecutor.executeNamedQueryList("getPicturesByAlbum", Picture.class, "album", album);
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

    public void updatePicture(Picture picture) {
    }

    public String getPath(Identifiable album) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Album) album).getUser().getLoginEmail())
                .append("/albums/")
                .append(Integer.toString(album.getId()));
        return sb.toString();
    }
}
