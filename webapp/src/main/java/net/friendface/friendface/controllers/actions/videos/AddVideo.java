package net.friendface.friendface.controllers.actions.videos;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 02.06.11
 * Time: 9:36
 */

public class AddVideo extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private File videoFile;
    private String videoTitle;

    @Override
    public String execute() {
        try {
            User user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            FriendfaceService.getService().getVideoService().addVideo(user, videoTitle, videoFile);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(videoTitle);
            validator.notNull(videoFile);
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    public void setVideoFile(File videoFile) {
        this.videoFile = videoFile;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public File getVideoFile() {
        return videoFile;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
