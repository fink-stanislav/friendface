package com.exadel.friendface.controllers.actions.concrete;

import com.exadel.friendface.controllers.actions.StrutsAction;
import com.exadel.friendface.view.beans.SearchPeopleBean;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Author: S. Fink
 * Date: 3/2/11
 * Time: 11:31 PM
 */

public class SearchPeople extends StrutsAction implements ModelDriven {
    private SearchPeopleBean searchPeopleBean = new SearchPeopleBean();

    @Override
    public String execute() {
        return SUCCESS;
    }

    public Object getModel() {
        return searchPeopleBean;
    }
}
