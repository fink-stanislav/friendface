package net.friendface.friendface.controllers.actions.search;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.queryhandling.SearchQueryParams;
import net.friendface.friendface.service.FriendfaceService;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 11:34
 */

public class SearchForPictures extends UserAction implements ModelDriven {
    private Picture picture = new Picture();
    private List<Picture> picturesFound;
    private Boolean notEmpty;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(picture.getTitle());
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        try {
            SearchQueryParams<String> queryParams = new SearchQueryParams<String>();
            queryParams.setParam("title", picture.getTitle());
            picturesFound = FriendfaceService.getService().getSearchService().searchForPictures(queryParams);
            notEmpty = !picturesFound.isEmpty();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public List<Picture> getPicturesFound() {
        return picturesFound;
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    @Override
    public Object getModel() {
        return picture;
    }
}
