package com.exadel.friendface.controllers.actions.search;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;

/**
 * Author: S. Fink
 * Date: 3/9/11
 * Time: 12:04 AM
 */

public class SearchVideos extends SessionAction {
    private String videoTitle;

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
        return SUCCESS;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
