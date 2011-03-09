package com.exadel.friendface.controllers.actions.user;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.model.entities.User;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/3/11
 * Time: 5:50 PM
 */

public class UserPage extends SessionAction {
    private User user;

    @Override
    public String execute() {
        try {
            user = getService().getUserService().getFromSession(getSession());
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
