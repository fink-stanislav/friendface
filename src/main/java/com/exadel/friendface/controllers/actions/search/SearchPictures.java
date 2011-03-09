package com.exadel.friendface.controllers.actions.search;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;

/**
 * Author: S. Fink
 * Date: 3/9/11
 * Time: 12:04 AM
 */

public class SearchPictures extends SessionAction {
    private String picTitle;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(picTitle);
        } catch (ValidationException e) {
            putToSession(SEARCH_ENTRY, "Pictures");
            putToSession(ACTION_MESSAGE, getText(e.toString()));
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }
}
