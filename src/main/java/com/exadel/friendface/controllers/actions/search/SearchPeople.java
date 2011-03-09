package com.exadel.friendface.controllers.actions.search;

import com.exadel.friendface.controllers.actions.StrutsAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import com.exadel.friendface.view.beans.SearchPeopleBean;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/2/11
 * Time: 11:31 PM
 */

public class SearchPeople extends StrutsAction implements ModelDriven, SessionAware {
    private SearchPeopleBean searchPeopleBean = new SearchPeopleBean();
    private Map session;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(3);
            validator.increasingNotBlank(searchPeopleBean.getLoginEmail());
            validator.increasingNotBlank(searchPeopleBean.getUsername());
            validator.increasingNotBlank(searchPeopleBean.getUserSurname());
        } catch (ValidationException e) {
            session.put(SEARCH_ENTRY, "People");
            session.put(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public Object getModel() {
        return searchPeopleBean;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
