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
 * Date: 3/12/11
 * Time: 1:58 AM
 */

public class SendProposal extends StandardAction implements ServletRequestAware, SessionAware {
    private Integer receiverId;
    private SessionHelper session;
    private RequestHelper requestHelper;

    @Override
    public String execute() {
        try {
            FriendfaceService.getService().getFriendsService().sendProposal(session, receiverId);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
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
