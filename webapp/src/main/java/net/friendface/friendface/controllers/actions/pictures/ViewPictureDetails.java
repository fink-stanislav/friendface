package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 14:24
 */

public class ViewPictureDetails extends UserAction {
    private Integer pictureId;
    private Picture picture;

    @Override
    public String execute() {
        try {
            picture = FriendfaceService.getService().getPicturesService().getPictureById(pictureId);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Picture getPicture() {
        return picture;
    }
}
