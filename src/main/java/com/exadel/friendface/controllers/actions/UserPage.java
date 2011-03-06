package com.exadel.friendface.controllers.actions;

import com.exadel.friendface.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.model.util.UserUtils.getUserSessionKey;

/**
 * User: S. Fink
 * Date: 2/3/11
 * Time: 5:50 PM
 */

public class UserPage extends StrutsAction implements SessionAware {
    private Map session;
    private User user;

    @Override
    public String execute() {
        try {
            setUser((User) session.get(getUserSessionKey()));
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
