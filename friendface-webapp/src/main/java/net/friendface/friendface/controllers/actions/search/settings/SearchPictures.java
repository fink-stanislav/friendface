package net.friendface.friendface.controllers.actions.search.settings;

import com.opensymphony.xwork2.Action;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;

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
        return Action.SUCCESS;
    }

    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }
}
