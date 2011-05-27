package net.friendface.friendface.view.beans;

import net.friendface.friendface.model.entities.Album;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 8:54 PM
 */

public class AlbumBean extends Album {
    private Long pictureCount;
    private Integer pictureId;

    public AlbumBean(Album album) {
        this.setId(album.getId());
        this.setTitle(album.getTitle());
        this.setUser(album.getUser());
    }

    public Long getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(Long pictureCount) {
        this.pictureCount = pictureCount;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }
}
