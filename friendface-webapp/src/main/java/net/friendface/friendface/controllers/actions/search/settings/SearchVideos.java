package net.friendface.friendface.controllers.actions.search.settings;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.ParameterHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
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
    private SessionHelper sessionHelper;
    private ParameterHelper parameters;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(videoTitle);
        } catch (ValidationException e) {
            sessionHelper.putToSession(SEARCH_ENTRY, "Videos");
            sessionHelper.putToSession(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        sessionHelper.putToSession(SEARCH_ENTRY, "Videos");
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
        this.parameters = new ParameterHelper(parameters);
    }

    public void setSession(Map session) {
        sessionHelper = new SessionHelper(session);
    }
}
