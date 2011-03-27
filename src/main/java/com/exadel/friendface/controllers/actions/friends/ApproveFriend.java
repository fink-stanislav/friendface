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
 * Date: 3/27/11
 * Time: 11:17 PM
 */

public class ApproveFriend extends StandardAction implements ServletRequestAware, SessionAware {
    private Integer id;
    private SessionHelper session;
    private HttpServletRequestHelper requestHelper;

    @Override
    public String execute() {
        try {
            getService().getFriendsService().approve(session, id);
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
        requestHelper = new HttpServletRequestHelper(request);
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
