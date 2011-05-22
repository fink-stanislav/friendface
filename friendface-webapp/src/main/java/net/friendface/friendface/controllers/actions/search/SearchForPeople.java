package net.friendface.friendface.controllers.actions.search;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.view.beans.UserBean;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 22:10
 */

public class SearchForPeople extends StandardAction {
    private String username;
    private String userSurname;
    private String loginEmail;
    private List<UserBean> peopleFound;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(3);
            validator.increasingNotBlank(username);
            validator.increasingNotBlank(userSurname);
            validator.increasingNotBlank(loginEmail);
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        try {
            FriendfaceService.getService().getSearchService().searchForPeople();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }
}
