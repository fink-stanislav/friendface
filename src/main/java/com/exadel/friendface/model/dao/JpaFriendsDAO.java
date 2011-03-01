package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;

import java.util.List;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:12 PM
 */

public class JpaFriendsDAO extends JpaDAO implements FriendsDAO {
    public JpaFriendsDAO() {
        super();
    }

    public void addFriend(Friend friend) throws Exception {
        persistEntity(friend);
    }

    public void deleteFriend(Friend friend) throws Exception {
        removeEntity(friend);
    }

    public Friend getFriend(Integer recordId) throws Exception {
        return executeNamedQuery("getFriendById", Friend.class, "recordId", recordId);
    }

    public List<Friend> getFriends(User user) throws Exception {
        return executeNamedQueryList("getUserFriends", Friend.class, "userId", user.getId());
    }
}
