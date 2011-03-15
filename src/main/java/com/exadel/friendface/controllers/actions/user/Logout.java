package com.exadel.friendface.controllers.actions.user;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.utils.SessionUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/4/11
 * Time: 1:53 PM
 */

public class Logout extends StandardAction implements SessionAware {
    private SessionUtils session;

    @Override
    public String execute() {
        try {
            getService().getUserService().logout(session.getSession());
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    public void setSession(Map session) {
        this.session = new SessionUtils(session);
    }
}
