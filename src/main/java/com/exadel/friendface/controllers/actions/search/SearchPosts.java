package com.exadel.friendface.controllers.actions.search;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;

/**
 * Author: S. Fink
 * Date: 3/9/11
 * Time: 12:04 AM
 */

public class SearchPosts extends SessionAction {
    private String postTitle;
    private String containText;

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
