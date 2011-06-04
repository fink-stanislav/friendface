package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;

import javax.jcr.RepositoryException;
import java.io.InputStream;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 8:41 PM
 */

public class ShowPicture extends StandardAction {
    private Integer pictureId;
    private InputStream inputStream;

    @Override
    public String execute() {
        try {
            Picture picture = FriendfaceService.getService().getPicturesService().getPictureById(pictureId);
            inputStream = picture.getContent().getStream();
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
