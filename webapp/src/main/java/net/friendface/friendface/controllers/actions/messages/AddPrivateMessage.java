package net.friendface.friendface.controllers.actions.messages;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 23:48
 */

public class AddPrivateMessage extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private Integer receiverId;
    private String message;

    @Override
    public String execute() {
        try {
            User sender = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            User receiver = FriendfaceService.getService().getUserService().getById(receiverId);
            FriendfaceService.getService().getMessagesService().postPrivateMessage(sender, receiver, message);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
