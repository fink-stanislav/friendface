package com.exadel.friendface.controllers.actions.search.settings;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import com.exadel.friendface.view.beans.SearchPeopleBean;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ParameterAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/2/11
 * Time: 11:31 PM
 */

public class SearchPeople extends SessionAction implements ModelDriven, ParameterAware {
    private SearchPeopleBean searchPeopleBean = new SearchPeopleBean();
    private Map parameters;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(3);
            validator.increasingNotBlank(searchPeopleBean.getLoginEmail());
            validator.increasingNotBlank(searchPeopleBean.getUsername());
            validator.increasingNotBlank(searchPeopleBean.getUserSurname());
        } catch (ValidationException e) {
            putToSession(SEARCH_ENTRY, "People");
            putToSession(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        putToSession(SEARCH_ENTRY, "People");
        parameters.put(SEARCH_ENTRY, "People");
        return SUCCESS;
    }

    public Object getModel() {
        return searchPeopleBean;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }
}
