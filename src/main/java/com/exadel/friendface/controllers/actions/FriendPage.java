package com.exadel.friendface.controllers.actions;

import com.exadel.friendface.model.entities.User;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 20.02.11
 * Time: 14:48
 */

public class FriendPage extends StrutsAction {
    private Integer id;
    private User user;

    @Override
    public String execute() {
        try {
            user = getService().getUserService().find(id);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
