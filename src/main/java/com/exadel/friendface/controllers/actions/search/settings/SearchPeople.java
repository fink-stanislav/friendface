package com.exadel.friendface.controllers.actions.search.settings;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import com.exadel.friendface.view.beans.SearchPeopleBean;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

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
