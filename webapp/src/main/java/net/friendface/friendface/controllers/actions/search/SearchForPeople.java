package net.friendface.friendface.controllers.actions.search;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
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
 * Date: 22.05.11
 * Time: 22:10
 */

public class SearchForPeople extends UserAction implements ModelDriven, SessionAware {
    private User user = new User();
    private SessionHelper sessionHelper;
    private List<UserBean> peopleFound;
    private Boolean notEmpty;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(3);
            validator.increasingNotBlank(user.getLoginEmail());
            validator.increasingNotBlank(user.getUsername());
            validator.increasingNotBlank(user.getUserSurname());
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        try {
            SearchQueryParams<String> queryParams = new SearchQueryParams<String>();
            queryParams.setParam("username", user.getUsername());
            queryParams.setParam("userSurname", user.getUserSurname());
            queryParams.setParam("loginEmail", user.getLoginEmail());
            User currentUser = (User) sessionHelper.getFromSession(getUserSessionKey());
            peopleFound = FriendfaceService.getService().getSearchService().searchForPeople(currentUser, queryParams);
            notEmpty = !peopleFound.isEmpty();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public List<UserBean> getPeopleFound() {
        return peopleFound;
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    @Override
    public Object getModel() {
        return user;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
