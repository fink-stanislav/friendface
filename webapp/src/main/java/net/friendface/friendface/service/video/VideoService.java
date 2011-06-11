package net.friendface.friendface.service.video;

import net.friendface.friendface.model.dao.DAOFactory;
import net.friendface.friendface.model.dao.videos.VideoDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.Video;

import javax.jcr.RepositoryException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 02.06.11
 * Time: 9:03
 */

public class VideoService {
    private static VideoService service;
    private VideoDAO videoDAO;

    public static VideoService getService() {
        if (service == null) {
            service = new VideoService();
        }
        return service;
    }

    private VideoService() {
        videoDAO = DAOFactory.getDAOFactory().getVideoDAO();
    }

    public Video getVideoById(Integer id) throws RepositoryException {
        return videoDAO.getById(id);
    }

    public void addVideo(User user, String title, File file) throws IOException {
        Video video = new Video();
        video.setUser(user);
        video.setTitle(title);
        video.setConverted(false);
        videoDAO.insertVideo(video);
        VideoConversionHandler.runConversion(file, video, videoDAO);
    }

    public void removeVideo(Video video) {
        videoDAO.deleteVideo(video);
    }

    public void renameVideo(Video video, String newTitle) {
        video.setTitle(newTitle);
        videoDAO.updateVideo(video);
    }

    public List<Video> getUserVideos(User user) {
        return videoDAO.getVideos(user);
    }
}
