package net.friendface.friendface.controllers.actions.pictures;

import net.friendface.friendface.controllers.actions.StandardAction;

/**
 * Author: S. Fink
 * Date: 5/10/11
 * Time: 11:29 PM
 */

public class AddPictureForm extends StandardAction {
    private Integer albumId;

    @Override
    public String execute() {
        return SUCCESS;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }
}
