package com.exadel.friendface.controllers.actions;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.model.util.UserUtils.getUserSessionKey;

/**
 * User: S. Fink
 * Date: 2/4/11
 * Time: 1:53 PM
 */

public class Logout extends StrutsAction implements SessionAware {
    private Map session;

    @Override
    public String execute() {
        try {
            logout();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    private void logout() throws Exception {
        session.put(getUserSessionKey(), null);
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
