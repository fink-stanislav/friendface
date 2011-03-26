package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.helpers.SessionHelper;
import com.exadel.friendface.model.entities.User;
import org.apache.commons.lang.StringUtils;
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
    private String nextAction;
    private SessionHelper session;

    @Override
    public String execute() {
        try {
            sendProposal();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void sendProposal() throws Exception {
        User sender = getService().getUserService().getFromSession(session);
        User receiver = getService().getUserService().getById(receiverId);
        getService().getFriendsService().sendProposal(sender, receiver);
    }

    public void setServletRequest(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        referer = StringUtils.substringAfterLast(referer, "/");
        nextAction = referer;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
