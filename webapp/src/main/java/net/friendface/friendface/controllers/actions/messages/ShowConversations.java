package net.friendface.friendface.controllers.actions.messages;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 23:22
 */

public class ShowConversations extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private List<User> recipients;
    private List<User> friendList;
    private Boolean notEmpty;

    @Override
    public String execute() {
        try {
            User me = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            recipients = FriendfaceService.getService().getMessagesService().getConversations(me);
            friendList = FriendfaceService.getService().getFriendsService().getApproved(me);
            notEmpty = !recipients.isEmpty();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
