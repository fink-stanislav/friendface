package com.exadel.friendface.controllers.actions.search.settings;

import com.exadel.friendface.controllers.actions.StandardAction;
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

public class SearchPosts extends SearchSettings {
    private String postTitle;
    private String containText;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator(2);
            validator.increasingNotBlank(postTitle);
            validator.increasingNotBlank(containText);
        } catch (ValidationException e) {
            handleValidationException(e, "Posts");
        }
    }

    @Override
    public String execute() {
        executeDefault("Posts");
        return SUCCESS;
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
