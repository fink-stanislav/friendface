package net.friendface.friendface.controllers.actions.welcome;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 26.02.11
 * Time: 22:27
 */

public class Entrance extends StandardAction implements SessionAware {
    private SessionHelper sessionHelper;
    private Integer userId;

    @Override
    public String execute() {
        try {
            User user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            if (user != null) {
                userId = user.getId();
            }
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
