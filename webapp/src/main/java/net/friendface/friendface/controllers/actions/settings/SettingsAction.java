package net.friendface.friendface.controllers.actions.settings;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 06.06.11
 * Time: 1:04
 */

public class SettingsAction extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private User user;

    @Override
    public String execute() {
        try {
            user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            userId = user.getId();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public User getUser() {
        return user;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
