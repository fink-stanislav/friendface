package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.enums.ContactState;

import java.util.List;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:05 PM
 */

public interface FriendsDAO {
    void setProposed(User sender, User receiver) throws Exception;
    void approve(Friend friend) throws Exception;
    void deleteFriend(Friend friend) throws Exception;
    Friend getById(Integer recordId) throws Exception;
    Friend getFriend(User receiver, User sender);
    List<Friend> getApproved(User user) throws Exception;
    List<Friend> getProposed(User user) throws Exception;
    List<Friend> getPending(User user) throws Exception;
}
