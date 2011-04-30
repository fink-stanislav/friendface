package net.friendface.friendface.controllers.actions.search.results;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.ParameterHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/12/11
 * Time: 1:44 AM
 */

public abstract class SearchResultAction extends StandardAction implements ParameterAware, SessionAware {
    private Boolean notEmpty = false;
    protected ParameterHelper parameters;
    protected SessionHelper session;

    @Override
    public String execute() {
        try {
            String searchEntry = parameters.getParameter(SEARCH_ENTRY);
            if (searchEntry != null) {
                parameters.removeParameter(SEARCH_ENTRY);
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

    public void setParameters(Map parameters) {
        this.parameters = new ParameterHelper(parameters);
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
