package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:56 PM
 */

public class RemovePicture extends UserAction implements ServletRequestAware {
    private RequestHelper requestHelper;
    private Integer pictureId;
    private Integer albumId;

    @Override
    public String execute() {
        try {
            PicturesService picturesService = FriendfaceService.getService().getPicturesService();
            Picture picture = picturesService.getPictureById(pictureId);
            picturesService.removePicture(picture);
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getNextAction() {
        return requestHelper.getPreviousAction();
    }

    public void setServletRequest(HttpServletRequest request) {
        requestHelper = new RequestHelper(request);
    }
}
