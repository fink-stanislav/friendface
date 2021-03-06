package net.friendface.friendface.controllers.actions.videos;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Video;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.video.VideoService;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 02.06.11
 * Time: 9:37
 */

public class RemoveVideo extends UserAction {
    private Integer videoId;

    @Override
    public String execute() {
        try {
            VideoService service = FriendfaceService.getService().getVideoService();
            Video video = service.getVideoById(videoId);
            service.removeVideo(video);
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
