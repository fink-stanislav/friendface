package net.friendface.friendface.controllers.actions.user;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.service.FriendfaceService;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * User: S. Fink
 * Date: 2/4/11
 * Time: 1:53 PM
 */

public class Logout extends StandardAction implements SessionAware {
    private SessionHelper session;

    @Override
    public String execute() {
        try {
            FriendfaceService.getService().getUserService().logout(session);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
