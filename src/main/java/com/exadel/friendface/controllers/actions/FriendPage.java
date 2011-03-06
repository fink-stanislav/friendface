package com.exadel.friendface.controllers.actions;

import com.exadel.friendface.model.entities.User;
import com.opensymphony.xwork2.ActionSupport;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;

/**
 * Author: S. Fink
 * Date: 20.02.11
 * Time: 14:48
 */

public class FriendPage extends ActionSupport {
    private Integer id;
    private User user;

    @Override
    public String execute() {
        try {
            findPageOwner();
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }

    private void findPageOwner() throws Exception {
        user = getDAOFactory().getUserDAO().getUser(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
