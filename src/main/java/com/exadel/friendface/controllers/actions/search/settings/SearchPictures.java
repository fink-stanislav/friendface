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

public class SearchPictures extends SearchSettings {
    private String picTitle;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.notBlank(picTitle);
        } catch (ValidationException e) {
            handleValidationException(e, "Pictures");
        }
    }

    @Override
    public String execute() {
        executeDefault("Pictures");
        return SUCCESS;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }
}
