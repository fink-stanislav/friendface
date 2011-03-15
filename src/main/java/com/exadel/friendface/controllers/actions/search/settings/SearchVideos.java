package com.exadel.friendface.controllers.actions.search.settings;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.utils.ParameterUtils;
import com.exadel.friendface.controllers.actions.utils.SessionUtils;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/9/11
 * Time: 12:04 AM
 */

public class SearchVideos extends StandardAction implements ParameterAware, SessionAware {
    private String videoTitle;
    private SessionUtils session;
    private ParameterUtils parameters;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(videoTitle);
        } catch (ValidationException e) {
            session.putToSession(SEARCH_ENTRY, "Videos");
            session.putToSession(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        session.putToSession(SEARCH_ENTRY, "Videos");
        parameters.setParameter(SEARCH_ENTRY, "Videos");
        return SUCCESS;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setParameters(Map parameters) {
        this.parameters = new ParameterUtils(parameters);
    }

    public void setSession(Map session) {
        this.session = new SessionUtils(session);
    }
}
