package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 8:41 PM
 */

public class ShowPicture extends StandardAction implements ServletRequestAware {
    private RequestHelper requestHelper;
    private Integer pictureId;
    private Picture picture;

    @Override
    public String execute() {
        try {
            picture = FriendfaceService.getService().getPicturesService().getPictureById(pictureId);
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Picture getPicture() {
        return picture;
    }

    public String getNextAction() {
        return requestHelper.getPreviousAction();
    }

    public void setServletRequest(HttpServletRequest request) {
        requestHelper = new RequestHelper(request);
    }
}
