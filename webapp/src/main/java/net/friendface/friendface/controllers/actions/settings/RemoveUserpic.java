package net.friendface.friendface.controllers.actions.settings;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 06.06.11
 * Time: 0:59
 */

public class RemoveUserpic extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;

    @Override
    public String execute() {
        try {
            User user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            FriendfaceService.getService().getUserService().removeUserpic(user);
            userId = user.getId();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
