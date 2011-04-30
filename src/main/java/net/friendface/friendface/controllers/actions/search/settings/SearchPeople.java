package net.friendface.friendface.controllers.actions.search.settings;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.view.beans.SearchPeopleBean;

/**
 * Author: S. Fink
 * Date: 3/2/11
 * Time: 11:31 PM
 */

public class SearchPeople extends SearchSettings implements ModelDriven {
    private SearchPeopleBean searchPeopleBean = new SearchPeopleBean();

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(3);
            validator.increasingNotBlank(searchPeopleBean.getLoginEmail());
            validator.increasingNotBlank(searchPeopleBean.getUsername());
            validator.increasingNotBlank(searchPeopleBean.getUserSurname());
        } catch (ValidationException e) {
            handleValidationException(e, "People");
        }
    }

    @Override
    public String execute() {
        executeDefault("People");
        return SUCCESS;
    }

    public Object getModel() {
        return searchPeopleBean;
    }
}
