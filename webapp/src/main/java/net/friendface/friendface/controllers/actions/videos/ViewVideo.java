package net.friendface.friendface.controllers.actions.videos;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Video;
import net.friendface.friendface.service.FriendfaceService;

/**
 * Author: S. Fink
 * Date: 03.06.11
 * Time: 23:25
 */

public class ViewVideo extends UserAction {
    private Integer videoId;
    private Video video;

    @Override
    public String execute() {
        try {
            video = FriendfaceService.getService().getVideoService().getVideoById(videoId);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Video getVideo() {
        return video;
    }
}
