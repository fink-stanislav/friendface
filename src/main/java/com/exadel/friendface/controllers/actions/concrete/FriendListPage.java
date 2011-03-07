package com.exadel.friendface.controllers.actions.concrete;

import com.exadel.friendface.controllers.actions.StrutsAction;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.service.FriendfaceService;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 2:24 PM
 */

public class FriendListPage extends StrutsAction implements SessionAware {
    private Map session;
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
            getFriends(getService().getUserService().getFromSession(session));
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

    public void setSession(Map session) {
        this.session = session;
    }
}
