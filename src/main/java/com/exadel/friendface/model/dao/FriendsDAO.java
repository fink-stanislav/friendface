package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;

import java.util.List;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:05 PM
 */

public interface FriendsDAO {
    void addFriend(Friend friend) throws Exception;
    void deleteFriend(Friend friend) throws Exception;
    Friend getFriend(Integer recordId) throws Exception;
    List<Friend> getFriends(User user) throws Exception;
}
