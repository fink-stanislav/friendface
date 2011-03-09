package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;

import java.util.Collection;
import java.util.List;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 2:24 PM
 */

public class FriendListPage extends SessionAction {
    private List<Friend> friends;
    private Boolean hasFriends;

    public Collection getFriends() {
        return friends;
    }

    public Boolean getHasFriends() {
        return hasFriends;
    }

    @Override
    public String execute() {
        try {
            getFriends(getService().getUserService().getFromSession(getSession()));
            return SUCCESS;
        }
        catch (Exception e) {
            return ERROR;
        }
    }

    private void getFriends(User user) throws Exception {
        friends = getService().getFriendsService().getFriends(user);
        hasFriends = friends.size() > 0;
    }
}
