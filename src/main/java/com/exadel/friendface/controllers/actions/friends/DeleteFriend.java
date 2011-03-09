package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.StrutsAction;
import com.exadel.friendface.model.entities.Friend;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 19.02.11
 * Time: 21:38
 */

public class DeleteFriend extends StrutsAction {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String execute() {
        try {
            Friend friend = getService().getFriendsService().getById(id);
            getService().getFriendsService().remove(friend);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }
}
