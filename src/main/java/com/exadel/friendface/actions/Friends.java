package com.exadel.friendface.actions;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.util.UserUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Collection;
import java.util.Map;

/**
 * User: sfink
 * Date: 2/7/11
 * Time: 2:24 PM
 */

public class Friends extends ActionSupport implements SessionAware {
    private Map session;
    private Collection friends;

    public Collection getFriends() {
        return friends;
    }

    public void setFriends(Collection friends) {
        this.friends = friends;
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
        setFriends(DAOFactory.getDAOFactory().getFriendsDAO().getFriends(user));
        return SUCCESS;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
