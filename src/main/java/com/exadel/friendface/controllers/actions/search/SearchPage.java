package com.exadel.friendface.controllers.actions.search;

import com.exadel.friendface.controllers.actions.SessionAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/8/11
 * Time: 7:25 PM
 */

public class SearchPage extends SessionAction {
    private List<String> searchEntries;
    private String searchEntry;

    public SearchPage() {
        searchEntries = new ArrayList<String>();
        searchEntries.add("People");
        searchEntries.add("Pictures");
        searchEntries.add("Posts");
        searchEntries.add("Videos");
        searchEntry = "People";
    }

    @Override
    public String execute() {
        String sessionValue = getFromSession(SEARCH_ENTRY);
        String errorMessage = getFromSession(ACTION_MESSAGE);

        if (sessionValue == null) {
            putToSession(SEARCH_ENTRY, searchEntry);
            return SUCCESS;
        }

        if (errorMessage != null) {
            searchEntry = sessionValue;
            removeFromSession(SEARCH_ENTRY);
            addActionMessage(errorMessage);
            removeFromSession(ACTION_MESSAGE);
        }
        return SUCCESS;
    }

    public List<String> getSearchEntries() {
        return searchEntries;
    }

    public void setSearchEntries(List<String> searchEntries) {
        this.searchEntries = searchEntries;
    }

    public String getSearchEntry() {
        return searchEntry;
    }

    public void setSearchEntry(String searchEntry) {
        this.searchEntry = searchEntry;
    }
}
