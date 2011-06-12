package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;

import javax.jcr.RepositoryException;
import java.io.File;
import java.io.IOException;

/**
 * Author: S. Fink
 * Date: 5/3/11
 * Time: 8:38 PM
 */

public class AddPicture extends UserAction {
    private File pictureFile;
    private String pictureTitle;
    private Integer albumId;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(pictureTitle);
            validator.notNull(pictureFile);
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    @Override
    public String execute() {
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

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setPictureFile(File pictureFile) {
        this.pictureFile = pictureFile;
    }

    public File getPictureFile() {
        return pictureFile;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
    }
}
