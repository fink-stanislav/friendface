package net.friendface.friendface.service.search;

import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.entities.Video;
import net.friendface.friendface.view.beans.UserBean;

import javax.jcr.RepositoryException;
import java.util.Collections;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 22:30
 */

public class SearchService {
    private static SearchService service;

    public static SearchService getService() {
        if (service == null) {
            service = new SearchService();
        }
        return service;
    }

    private SearchService() {
    }

    public List<UserBean> searchForPeople() {
        return Collections.emptyList();
    }

    public List<Picture> searchForPictures() {
        return Collections.emptyList();
    }

    public List<Video> searchForVideos() {
        return Collections.emptyList();
    }
}
