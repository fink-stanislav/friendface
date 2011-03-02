package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.SearchPeopleBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Author: S. Fink
 * Date: 3/2/11
 * Time: 11:31 PM
 */

public class SearchPeople extends ActionSupport implements ModelDriven {
    private SearchPeopleBean searchPeopleBean = new SearchPeopleBean();

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public Object getModel() {
        return searchPeopleBean;
    }
}
