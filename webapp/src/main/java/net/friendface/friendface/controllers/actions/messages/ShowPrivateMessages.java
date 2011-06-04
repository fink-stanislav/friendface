package net.friendface.friendface.controllers.actions.messages;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.PrivateMessage;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserUtils;
import net.friendface.friendface.view.beans.PrivateMessageBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 20:53
 */

public class ShowPrivateMessages extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private List<PrivateMessageBean<PrivateMessage>> messageList;
    private List<User> friendList;
    private Integer senderId;
    private Boolean notEmpty;

    @Override
    public String execute() {
        try {
            User receiver = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            User sender = FriendfaceService.getService().getUserService().getById(senderId);
            messageList = FriendfaceService.getService().getMessagesService().getPrivateMessages(receiver, sender);
            friendList = FriendfaceService.getService().getFriendsService().getApproved(receiver);
            notEmpty = !messageList.isEmpty();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public List<PrivateMessageBean<PrivateMessage>> getMessageList() {
        return messageList;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
