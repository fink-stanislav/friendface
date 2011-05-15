package net.friendface.friendface.controllers.actions.pictures;

/**
 * Author: S. Fink
 * Date: 5/9/11
 * Time: 6:56 PM
 */

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.model.entities.Album;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.pictures.PicturesService;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowPictures extends StandardAction implements ServletRequestAware {
    private RequestHelper requestHelper;
    private List<Picture> pictureList;
    private Integer albumId;
    private String albumTitle;
    private Integer pictureCount;
    private Boolean hasPictures;

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

    public Integer getPictureCount() {
        return pictureCount;
    }

    public Boolean getHasPictures() {
        return hasPictures;
    }

    public String getNextAction() {
        return requestHelper.getPreviousAction();
    }

    public void setServletRequest(HttpServletRequest request) {
        requestHelper = new RequestHelper(request);
    }
}
