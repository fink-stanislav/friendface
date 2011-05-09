package net.friendface.friendface.controllers.actions.search.settings;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.ParameterHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:46
 */

public abstract class SearchSettings extends StandardAction implements ParameterAware, SessionAware {
    protected ParameterHelper parameters;
    protected SessionHelper sessionHelper;

    public void handleValidationException(ValidationException exception, String searchEntry) {
        sessionHelper.putToSession(SEARCH_ENTRY, searchEntry);
        sessionHelper.putToSession(ACTION_MESSAGE, getText(exception.toString()));
        addActionError(getText(exception.toString()));
    }

    public void executeDefault(String searchEntry) {
        sessionHelper.putToSession(SEARCH_ENTRY, searchEntry);
        parameters.setParameter(SEARCH_ENTRY, searchEntry);
    }

    public void setParameters(Map parameters) {
        this.parameters = new ParameterHelper(parameters);
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
