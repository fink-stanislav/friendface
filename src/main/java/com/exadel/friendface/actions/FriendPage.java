package com.exadel.friendface.actions;

import com.exadel.friendface.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Author: S. Fink
 * Date: 20.02.11
 * Time: 14:48
 */

public class FriendPage extends ActionSupport {
    private User user;

    @Override
    public String execute() {
        try {
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
