package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 8:38 PM
 */

public class AddPicture extends StandardAction implements SessionAware, ServletRequestAware {
    private SessionHelper sessionHelper;
    private RequestHelper requestHelper;
    private File pictureFile;
    private String pictureTitle;
    private Integer albumId;

    @Override
    public String execute() {
        User user = (User) sessionHelper.getFromSession(getUserSessionKey());
        try {
            PicturesService service = FriendfaceService.getService().getPicturesService();
            Album album = service.getAlbumById(albumId);
            service.addPicture(album, pictureTitle, pictureFile);
        } catch (RepositoryException e) {
            resultAndErrorMessage(ERROR, e.getMessage());
        } catch (IOException e) {
            resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setPictureFile(File pictureFile) {
        this.pictureFile = pictureFile;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
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
