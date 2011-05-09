package net.friendface.friendface.controllers.actions.user;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.view.beans.WallMessageBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * User: S. Fink
 * Date: 2/3/11
 * Time: 5:50 PM
 */

public class UserPage extends StandardAction implements SessionAware {
    private User user;
    private List<WallMessageBean> messageList;
    private SessionHelper sessionHelper;
    private Boolean hasWallMessages;

    @Override
    public String execute() {
        try {
            user = (User) sessionHelper.getFromSession(getUserSessionKey());
            messageList = FriendfaceService.getService().getMessagesService().getWallMessages(user);
            hasWallMessages = !messageList.isEmpty();
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WallMessageBean> getMessageList() {
        return messageList;
    }

    public Boolean getHasWallMessages() {
        return hasWallMessages;
    }

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
