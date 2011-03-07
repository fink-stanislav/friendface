package com.exadel.friendface.controllers.actions.common;

import com.exadel.friendface.controllers.actions.StrutsAction;
import com.exadel.friendface.model.entities.User;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

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
            user = getService().getUserService().getFromSession(session);
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
