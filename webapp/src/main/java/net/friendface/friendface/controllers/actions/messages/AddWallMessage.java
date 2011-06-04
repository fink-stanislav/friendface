package net.friendface.friendface.controllers.actions.messages;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserService;
import org.apache.struts2.interceptor.SessionAware;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * Author: S. Fink
 * Date: 4/3/11
 * Time: 9:12 PM
 */

public class AddWallMessage extends UserAction implements SessionAware {
    private String message;
    private SessionHelper sessionHelper;

    @Override
    public String execute() {
        try {
            UserService userService = FriendfaceService.getService().getUserService();
            User receiver = userService.getById(userId);
            User sender = (User) sessionHelper.getFromSession(getUserSessionKey());
            FriendfaceService.getService().getMessagesService().postWallMessage(sender, receiver, message);
            return SUCCESS;
        } catch (RepositoryException e) {
            return ERROR;
        } catch (IOException e) {
            return ERROR;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
