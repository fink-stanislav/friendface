package net.friendface.friendface.controllers.actions.friends;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.RequestHelper;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.service.FriendfaceService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 19.02.11
 * Time: 21:38
 */

public class DeleteFriend extends StandardAction implements ServletRequestAware, SessionAware {
    private RequestHelper requestHelper;
    private SessionHelper session;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String execute() {
        try {
            FriendfaceService.getService().getFriendsService().remove(session, id);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    public String getNextAction() {
        return requestHelper.getPreviousAction();
    }

    public void setServletRequest(HttpServletRequest request) {
        requestHelper = new RequestHelper(request);
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
