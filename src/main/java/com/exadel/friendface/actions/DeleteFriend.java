package com.exadel.friendface.actions;

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
    private Integer friendId;
    private Integer userId;

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        Friend friend = new Friend();
        friend.setFriendId(friendId);
        friend.setUserId(userId);
        friendsDAO.deleteFriend(friend);
        return SUCCESS;
    }
}
