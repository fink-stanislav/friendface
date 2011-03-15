package com.exadel.friendface.controllers.actions.search.settings;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.utils.ParameterUtils;
import com.exadel.friendface.controllers.actions.utils.SessionUtils;
import com.exadel.friendface.controllers.validation.ValidationException;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:46
 */

public abstract class SearchSettings extends StandardAction implements ParameterAware, SessionAware {
    protected ParameterUtils parameters;
    protected SessionUtils session;

    public void handleValidationException(ValidationException exception, String searchEntry) {
        session.putToSession(SEARCH_ENTRY, searchEntry);
        session.putToSession(ACTION_MESSAGE, getText(exception.toString()));
        addActionError(getText(exception.toString()));
    }

    public void executeDefault(String searchEntry) {
        session.putToSession(SEARCH_ENTRY, searchEntry);
        parameters.setParameter(SEARCH_ENTRY, searchEntry);
    }

    public void setParameters(Map parameters) {
        this.parameters = new ParameterUtils(parameters);
    }

    public void setSession(Map session) {
        this.session = new SessionUtils(session);
    }
}
