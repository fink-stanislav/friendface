package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.helpers.HttpServletRequestHelper;
import com.exadel.friendface.controllers.actions.helpers.SessionHelper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 3/12/11
 * Time: 1:58 AM
 */

public class SendProposal extends StandardAction implements ServletRequestAware, SessionAware {
    private Integer receiverId;
    private SessionHelper session;
    private HttpServletRequestHelper requestHelper;

    @Override
    public String execute() {
        try {
            getService().getFriendsService().sendProposal(session, receiverId);
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
        requestHelper = new HttpServletRequestHelper(request);
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
