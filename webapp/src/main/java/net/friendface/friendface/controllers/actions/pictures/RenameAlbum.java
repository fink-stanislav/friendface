package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;

/**
 * Author: S. Fink
 * Date: 30.05.11
 * Time: 21:34
 */

public class RenameAlbum extends UserAction {
    private Integer albumId;
    private String newAlbumTitle;

    @Override
    public String execute() {
        try {
            PicturesService service = FriendfaceService.getService().getPicturesService();
            Album album = service.getAlbumById(albumId);
            service.renameAlbum(album, newAlbumTitle);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getNewAlbumTitle() {
        return newAlbumTitle;
    }

    public void setNewAlbumTitle(String newAlbumTitle) {
        this.newAlbumTitle = newAlbumTitle;
    }
}
