package net.friendface.friendface.controllers.actions.videos;

import net.friendface.friendface.controllers.actions.SecurityAware;
import net.friendface.friendface.controllers.actions.SecuritySettings;
import net.friendface.friendface.controllers.actions.UserAction;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 20:45
 */

public class ShowVideos extends UserAction implements SecurityAware {
    private Boolean showControls;

    @Override
    public String execute() {
        return SUCCESS;
    }

    public Boolean getShowControls() {
        return showControls;
    }

    @Override
    public void setSecuritySettings(SecuritySettings settings) {
        showControls = settings.getEqualIds();
    }
}
