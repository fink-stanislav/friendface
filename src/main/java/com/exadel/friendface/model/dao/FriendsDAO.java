package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;

import java.util.Collection;

/**
 * User: sfink
 * Date: 2/7/11
 * Time: 1:05 PM
 */

public interface FriendsDAO {
    int addFriend(Friend friend) throws Exception;
    int deleteFriend(Friend friend) throws Exception;
    Collection getFriends(User user) throws Exception;
}
