package net.friendface.friendface.controllers.actions.pictures;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:56 PM
 */

import net.friendface.friendface.controllers.actions.SecurityAware;
import net.friendface.friendface.controllers.actions.SecuritySettings;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;

import javax.jcr.RepositoryException;
import java.util.List;

public class ShowPictures extends UserAction implements SecurityAware {
    private List<Picture> pictureList;
    private Integer albumId;
    private String albumTitle;
    private Long pictureCount;
    private Boolean hasPictures;
    private Boolean showControls;

    @Override
    public String execute() {
        try {
            PicturesService picturesService = FriendfaceService.getService().getPicturesService();
            Album album = picturesService.getAlbumById(albumId);
            albumTitle = album.getTitle();
            pictureCount = picturesService.getAlbumPictureCount(album);
            pictureList = picturesService.getAlbumPictures(album);
            hasPictures = !pictureList.isEmpty();
        } catch (RepositoryException e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public Long getPictureCount() {
        return pictureCount;
    }

    public Boolean getHasPictures() {
        return hasPictures;
    }

    public Boolean getShowControls() {
        return showControls;
    }

    @Override
    public void setSecuritySettings(SecuritySettings settings) {
        showControls = settings.getEqualIds();
    }
}
