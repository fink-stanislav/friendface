package com.exadel.friendface.model.dao.friends;

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
    void setProposed(User sender, User receiver);

    void approve(Friend friend);

    void deleteFriend(Friend friend);

    Friend getById(Integer recordId);

    Friend getFriend(User receiver, User sender);

    List<Friend> getApproved(User user);

    List<Friend> getProposed(User user);

    List<Friend> getPending(User user);
}
