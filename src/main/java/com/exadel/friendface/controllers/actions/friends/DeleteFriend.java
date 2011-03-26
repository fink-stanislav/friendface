package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.helpers.SessionHelper;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;
import static org.apache.commons.lang.StringUtils.substringAfterLast;

/**
 * Author: S. Fink
 * Date: 19.02.11
 * Time: 21:38
 */

public class DeleteFriend extends StandardAction implements ServletRequestAware, SessionAware {
    private Integer id;
    private SessionHelper session;
    private String nextAction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String execute() {
        try {
            deleteFriend();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void deleteFriend() throws Exception {
        User owner = getService().getUserService().getFromSession(session);
        User friend = getService().getUserService().getById(id);
        Friend result = getService().getFriendsService().getFriend(owner, friend);
        getService().getFriendsService().remove(result);
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setServletRequest(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        referer = substringAfterLast(referer, "/");
        nextAction = referer;
    }

    public void setSession(Map session) {
        this.session = new SessionHelper(session);
    }
}
