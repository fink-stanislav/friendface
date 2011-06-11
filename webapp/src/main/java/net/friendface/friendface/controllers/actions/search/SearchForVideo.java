package net.friendface.friendface.controllers.actions.search;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.Video;
import net.friendface.friendface.model.queryhandling.SearchQueryParams;
import net.friendface.friendface.service.FriendfaceService;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 12.06.11
 * Time: 0:42
 */

public class SearchForVideo extends UserAction implements ModelDriven {
    private Video video = new Video();
    private List<Video> videosFound;
    private Boolean notEmpty;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(video.getTitle());
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        try {
            SearchQueryParams<String> queryParams = new SearchQueryParams<String>();
            queryParams.setParam("title", video.getTitle());
            videosFound = FriendfaceService.getService().getSearchService().searchForVideos(queryParams);
            notEmpty = !videosFound.isEmpty();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public List<Video> getVideosFound() {
        return videosFound;
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    @Override
    public Object getModel() {
        return video;
    }
}
