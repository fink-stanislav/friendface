package net.friendface.friendface.controllers.actions.user;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * User: S. Fink
 * Date: 2/3/11
 * Time: 5:50 PM
 */

public class UserPage extends StandardAction implements SessionAware {
    private User user;
    private SessionHelper session;

    @Override
    public String execute() {
        try {
            user = FriendfaceService.getService().getUserService().getFromSession(session);
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
        this.session = new SessionHelper(session);
    }
}
