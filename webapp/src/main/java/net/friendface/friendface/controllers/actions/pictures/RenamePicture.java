package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 30.05.11
 * Time: 22:28
 */

public class RenamePicture extends UserAction {
    private Integer pictureId;
    private String newPictureTitle;

    @Override
    public String execute() {
        try {
            PicturesService service = FriendfaceService.getService().getPicturesService();
            Picture picture = service.getPictureById(pictureId);
            service.renamePicture(picture, newPictureTitle);
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getNewPictureTitle() {
        return newPictureTitle;
    }

    public void setNewPictureTitle(String newPictureTitle) {
        this.newPictureTitle = newPictureTitle;
    }
}
