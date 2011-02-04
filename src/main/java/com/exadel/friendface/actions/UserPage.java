package com.exadel.friendface.actions;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.model.dao.DAOFactory.StorageEngineType.mysql;
import static com.exadel.friendface.system.FriendfaceConstants.FriendfaceUser;

/**
 * User: sfink
 * Date: 2/3/11
 * Time: 5:50 PM
 */

public class UserPage extends ActionSupport implements SessionAware {
    private Map session;
    private User user;

    @Override
    public String execute() {
        try {
            User user = (User) session.get(FriendfaceUser);
            user = DAOFactory.getDAOFactory(mysql).getUserDAO().getUser(user.getLoginEmail());
            session.put(FriendfaceUser, user);
            setUser(user);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
