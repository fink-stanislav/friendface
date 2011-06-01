package net.friendface.friendface.model.dao.videos;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.*;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.DefaultQueryParams;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 02.06.11
 * Time: 0:54
 */

public class VideoDAOImpl extends EntityDAO implements VideoDAO {
    public VideoDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    @Override
    public List<Video> getVideos(User user) {
        DefaultQueryParams<User> queryParams = new DefaultQueryParams<User>("getVideosByUser");
        queryParams.setParam("user", user);
        return queryExecutor.executeNamedQueryList(queryParams, Video.class);
    }

    @Override
    public Video getById(Integer id) throws RepositoryException {
        try {
            Video video = getById(id, Video.class);
            return repositoryManager.retrieveContent(video, getPath(video));
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insertVideo(Video video) {
        perform(new Operation<Video>(video) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
                repositoryManager.storeContent(entity, getPath(entity));
            }
        });
    }

    @Override
    public void updateVideo(Video video) {
        perform(new Operation<Video>(video) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.merge(entity);
            }
        });
    }

    @Override
    public void deleteVideo(Video video) {
        perform(new Operation<Video>(video) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.remove(entity);
                repositoryManager.removeContent(entity, getPath(entity));
            }
        });
    }

    public String getPath(Identifiable video) {
        StringBuilder sb = new StringBuilder();
        sb.append(((Video) video).getUser().getLoginEmail())
                .append(Integer.toString(video.getId()));
        return sb.toString();
    }
}
