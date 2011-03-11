package com.exadel.friendface.controllers.actions.search.settings;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import org.apache.struts2.interceptor.ParameterAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 3/9/11
 * Time: 12:04 AM
 */

public class SearchVideos extends SessionAction implements ParameterAware {
    private String videoTitle;
    private Map parameters;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(videoTitle);
        } catch (ValidationException e) {
            putToSession(SEARCH_ENTRY, "Videos");
            putToSession(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        putToSession(SEARCH_ENTRY, "Videos");
        parameters.put(SEARCH_ENTRY, "Videos");
        return SUCCESS;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
