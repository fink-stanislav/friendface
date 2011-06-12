package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;

import javax.jcr.Binary;
import java.io.InputStream;

/**
 * Author: S. Fink
 * Date: 10.06.11
 * Time: 8:20
 */

public class ShowAlbumPicture extends StandardAction {
    private Integer albumId;
    private InputStream inputStream;

    @Override
    public String execute() {
        try {
            Album album = FriendfaceService.getService().getPicturesService().getAlbumById(albumId);
            Picture picture = FriendfaceService.getService().getPicturesService().getAlbumPicture(album);
            if (picture == null) {
                inputStream = getClass().getResourceAsStream("/images/no_image.png");
                return SUCCESS;
            }
            Binary content = picture.getContent();
            if (content == null) {
                inputStream = getClass().getResourceAsStream("/images/no_image.png");
            } else {
                inputStream = content.getStream();
            }
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
