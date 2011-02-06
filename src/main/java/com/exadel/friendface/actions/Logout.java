package com.exadel.friendface.actions;

import com.exadel.friendface.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;
import static com.exadel.friendface.model.util.UserUtils.getUserSessionKey;

/**
 * User: sfink
 * Date: 2/4/11
 * Time: 1:53 PM
 */

public class Logout extends ActionSupport implements SessionAware {
    private Map session;

    public String execute() {
        try {
            logout();
            return SUCCESS;
        } catch (Exception e) {
            addActionError("Internal application error. " + e.getMessage());
            return ERROR;
        }
    }

    private void logout() throws Exception {
        User user = (User) session.get(getUserSessionKey());
        getDAOFactory().getAuthorizationDAO().logoutUser(user);
        session.put(getUserSessionKey(), null);
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
