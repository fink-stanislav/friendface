package com.exadel.friendface.controllers.actions.search.results;

import com.exadel.friendface.controllers.actions.ParametrizedAction;

/**
 * Author: S. Fink
 * Date: 3/12/11
 * Time: 1:44 AM
 */

public abstract class SearchResultAction extends ParametrizedAction {
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

    public abstract void performSearch(String searchEntry) throws Exception;

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(Boolean notEmpty) {
        this.notEmpty = notEmpty;
    }
}
