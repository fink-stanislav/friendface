package net.friendface.friendface.controllers.actions.videos;

import net.friendface.friendface.controllers.actions.SecurityAware;
import net.friendface.friendface.controllers.actions.SecuritySettings;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.Video;
import net.friendface.friendface.service.FriendfaceService;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 20:45
 */

public class ShowVideos extends UserAction implements SecurityAware {
    private List<Video> videosList;
    private Boolean showControls;
    private Boolean showMenu;
    private Boolean hasVideos;

    @Override
    public String execute() {
        try {
            User user = FriendfaceService.getService().getUserService().getById(userId);
            videosList = FriendfaceService.getService().getVideoService().getUserVideos(user);
            hasVideos = !videosList.isEmpty();

            showMenu = true;
            if (hasVideos) {
                for (Video video : videosList) {
                    if (!video.getConverted()) {
                        showMenu = false;
                        break;
                    }
                }
            }

            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Boolean getHasVideos() {
        return hasVideos;
    }

    public List<Video> getVideosList() {
        return videosList;
    }

    public Boolean getShowMenu() {
        return showMenu;
    }

    public Boolean getShowControls() {
        return showControls;
    }

    @Override
    public void setSecuritySettings(SecuritySettings settings) {
        showControls = settings.getEqualIds();
    }
}
