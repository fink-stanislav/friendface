package com.exadel.friendface.controllers.actions.search.results;

import com.exadel.friendface.controllers.actions.ParametrizedAction;
import com.exadel.friendface.model.entities.User;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/10/11
 * Time: 10:20 PM
 */

public class VideosFound extends ParametrizedAction {
    private List<User> resultList;
    private Boolean notEmpty = false;

    @Override
    public String execute() {
        try {
            String searchEntry = getParameter(SEARCH_ENTRY);
            if (searchEntry != null) {
                removeParameter(SEARCH_ENTRY);
                performSearch(searchEntry);
            }
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void performSearch(String searchEntry) throws Exception {
        notEmpty = false;
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    public List<User> getResultList() {
        return resultList;
    }
}
