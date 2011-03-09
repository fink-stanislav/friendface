package com.exadel.friendface.controllers.actions.user;

import com.exadel.friendface.controllers.actions.SessionAction;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/4/11
 * Time: 1:53 PM
 */

public class Logout extends SessionAction {
    @Override
    public String execute() {
        try {
            getService().getUserService().logout(getSession());
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }
}
