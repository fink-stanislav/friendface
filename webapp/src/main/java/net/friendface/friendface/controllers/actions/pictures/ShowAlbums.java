package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.SecurityAware;
import net.friendface.friendface.controllers.actions.SecuritySettings;
import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import net.friendface.friendface.view.beans.AlbumBean;
import org.apache.struts2.interceptor.SessionAware;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:26 PM
 */

public class ShowAlbums extends StandardAction implements SessionAware, SecurityAware {
    private SessionHelper sessionHelper;
    private List<AlbumBean> albumList;
    private Boolean hasAlbums;
    private Boolean showControls;

    @Override
    public String execute() {
        try {
            User user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            albumList = FriendfaceService.getService().getPicturesService().getUserAlbums(user);
            hasAlbums = !albumList.isEmpty();
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public Boolean getHasAlbums() {
        return hasAlbums;
    }

    public List<AlbumBean> getAlbumList() {
        return albumList;
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }

    public Boolean getShowControls() {
        return showControls;
    }

    @Override
    public void setSecuritySettings(SecuritySettings settings) {
        showControls = settings.getEqualIds();
    }
}
