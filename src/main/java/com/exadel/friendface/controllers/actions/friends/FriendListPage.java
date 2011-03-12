package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.SessionAction;
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
    private List<User> friends;
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
            getApprovedFriends(getService().getUserService().getFromSession(getSession()));
            hasFriends = !friends.isEmpty();
            return SUCCESS;
        }
        catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void getApprovedFriends(User user) throws Exception {
        friends = getService().getFriendsService().getApproved(user);
    }

    private void getProposedFriends(User user) throws Exception {
        friends = getService().getFriendsService().getProposed(user);
    }

    private void getPendingFriends(User user) throws Exception {
        friends = getService().getFriendsService().getPendings(user);
    }
}
