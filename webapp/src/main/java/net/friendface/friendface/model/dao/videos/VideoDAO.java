package net.friendface.friendface.model.dao.videos;

import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.Video;

import javax.jcr.RepositoryException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 02.06.11
 * Time: 0:54
 */

public interface VideoDAO {
    List<Video> getVideos(User user);

    Video getById(Integer id) throws RepositoryException;

    void insertVideo(Video video);

    void updateVideo(Video video);

    void deleteVideo(Video video);
}
