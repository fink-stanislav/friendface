package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import javax.jcr.RepositoryException;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:26 PM
 */

public class AddAlbum extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private String albumTitle;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(albumTitle);
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    @Override
    public String execute() {
        User user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
        try {
            FriendfaceService.getService().getPicturesService().addAlbum(user, albumTitle);
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
