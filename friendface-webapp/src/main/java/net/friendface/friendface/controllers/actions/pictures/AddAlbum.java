package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:26 PM
 */

public class AddAlbum extends StandardAction implements SessionAware, ServletRequestAware {
    private SessionHelper sessionHelper;
    private RequestHelper requestHelper;
    private String albumTitle;

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

    public String getNextAction() {
        return requestHelper.getPreviousAction();
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }

    public void setServletRequest(HttpServletRequest request) {
        requestHelper = new RequestHelper(request);
    }
}
