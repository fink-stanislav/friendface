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
 * Date: 19.02.11
 * Time: 21:38
 */

public class DeleteFriend extends StandardAction implements ServletRequestAware, SessionAware {
    private HttpServletRequestHelper requestHelper;
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
            getService().getFriendsService().remove(session, id);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
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
