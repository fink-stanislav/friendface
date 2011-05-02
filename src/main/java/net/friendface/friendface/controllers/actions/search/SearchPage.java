package net.friendface.friendface.controllers.actions.search;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/8/11
 * Time: 7:25 PM
 */

public class SearchPage extends StandardAction implements SessionAware {
    private List<String> searchEntries;
    private String searchEntry;
    private SessionHelper sessionHelper;

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
        try {
            return prepareSearch();
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private String prepareSearch() throws Exception {
        String sessionValue = sessionHelper.getFromSession(SEARCH_ENTRY);
        String errorMessage = sessionHelper.getFromSession(ACTION_MESSAGE);

        if (sessionValue == null) {
            sessionHelper.putToSession(SEARCH_ENTRY, searchEntry);
            return SUCCESS;
        }

        if (errorMessage != null) {
            addActionMessage(errorMessage);
            sessionHelper.removeFromSession(ACTION_MESSAGE);
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
        return sessionHelper.getFromSession(SEARCH_ENTRY);
    }

    public void setSearchEntry(String searchEntry) {
        sessionHelper.putToSession(SEARCH_ENTRY, searchEntry);
    }

    public void setSession(Map session) {
        sessionHelper = new SessionHelper(session);
    }
}
