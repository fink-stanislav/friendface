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

public class SearchPosts extends SessionAction implements ParameterAware {
    private String postTitle;
    private String containText;
    private Map parameters;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(2);
            validator.increasingNotBlank(postTitle);
            validator.increasingNotBlank(containText);
        } catch (ValidationException e) {
            putToSession(SEARCH_ENTRY, "Posts");
            putToSession(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        putToSession(SEARCH_ENTRY, "Posts");
        parameters.put(SEARCH_ENTRY, "Posts");
        return SUCCESS;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getContainText() {
        return containText;
    }

    public void setContainText(String containText) {
        this.containText = containText;
    }
}
