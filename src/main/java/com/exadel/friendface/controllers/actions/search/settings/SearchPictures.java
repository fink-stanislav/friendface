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

public class SearchPictures extends SessionAction implements ParameterAware {
    private String picTitle;
    private Map parameters;

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
        putToSession(SEARCH_ENTRY, "Pictures");
        parameters.put(SEARCH_ENTRY, "Pictures");
        return SUCCESS;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }
}
