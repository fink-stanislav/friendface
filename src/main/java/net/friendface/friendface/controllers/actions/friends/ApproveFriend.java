package net.friendface.friendface.controllers.actions.friends;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * Author: S. Fink
 * Date: 3/27/11
 * Time: 11:17 PM
 */

public class ApproveFriend extends StandardAction implements ServletRequestAware, SessionAware {
    private Integer id;
    private SessionHelper sessionHelper;
    private RequestHelper requestHelper;

    @Override
    public String execute() {
        try {
            User owner = sessionHelper.getFromSession(getUserSessionKey());
            User friend = UserService.getService().getById(id);
            FriendfaceService.getService().getFriendsService().approve(owner, friend);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNextAction() {
        return requestHelper.getPreviousAction();
    }

    public void setServletRequest(HttpServletRequest request) {
        requestHelper = new RequestHelper(request);
    }

    public void setSession(Map session) {
        sessionHelper = new SessionHelper(session);
    }
}
