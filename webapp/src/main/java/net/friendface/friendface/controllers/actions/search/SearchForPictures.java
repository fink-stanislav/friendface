package net.friendface.friendface.controllers.actions.search;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.queryhandling.SearchQueryParams;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.view.beans.UserBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 11:34
 */

public class SearchForPictures extends StandardAction implements ModelDriven {
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