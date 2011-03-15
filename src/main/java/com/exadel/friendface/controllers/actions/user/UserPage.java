package com.exadel.friendface.controllers.actions.user;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.utils.ParameterUtils;
import com.exadel.friendface.controllers.actions.utils.SessionUtils;
import com.exadel.friendface.model.entities.User;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/3/11
 * Time: 5:50 PM
 */

public class UserPage extends StandardAction implements SessionAware {
    private User user;
    private SessionUtils session;

    @Override
    public String execute() {
        try {
            user = getService().getUserService().getFromSession(session.getSession());
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

    public void setSession(Map session) {
        this.session = new SessionUtils(session);
    }
}
