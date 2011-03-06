package com.exadel.friendface.controllers.actions;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.util.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
            return showFriends();
        }
        catch (Exception e) {
            return ERROR;
        }
    }

    private String showFriends() throws Exception {
        User user = (User) session.get(UserUtils.getUserSessionKey());
        friends = DAOFactory.getDAOFactory().getFriendsDAO().getFriends(user);
        hasFriends = friends.size() > 0;
        return SUCCESS;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
