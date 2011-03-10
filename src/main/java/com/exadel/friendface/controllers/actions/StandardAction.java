package com.exadel.friendface.controllers.actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 1:09 AM
 */

public abstract class StandardAction extends ActionSupport implements FriendfaceAction {
    @Override
    public abstract String execute();

    public String resultAndErrorMessage(String result, String errorMessage) {
        addActionError(errorMessage);
        return result;
    }
}
