package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:26 PM
 */

public class RemoveAlbum extends UserAction {
    private Integer albumId;

    @Override
    public String execute() {
        try {
            PicturesService picturesService = FriendfaceService.getService().getPicturesService();
            Album album = picturesService.getAlbumById(albumId);
            picturesService.removeAlbum(album);
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getAlbumId() {
        return albumId;
    }
}
