package com.exadel.friendface.controllers.actions;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.FriendsDAO;
import com.exadel.friendface.model.entities.Friend;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Author: S. Fink
 * Date: 19.02.11
 * Time: 21:38
 */

public class DeleteFriend extends ActionSupport {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String execute() {
        try {
            return deleteFriend();
        } catch (Exception e) {
            return ERROR;
        }
    }

    private String deleteFriend() throws Exception {
        FriendsDAO friendsDAO = DAOFactory.getDAOFactory().getFriendsDAO();
        Friend friend = friendsDAO.getFriend(id);
        friendsDAO.deleteFriend(friend);
        return SUCCESS;
    }
}
