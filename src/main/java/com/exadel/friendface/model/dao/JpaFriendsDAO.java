package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;

import javax.persistence.NoResultException;
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

    public Friend getFriend(Integer friendId) throws Exception {
        try {
            return find(friendId, Friend.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Friend> getFriends(User user) throws Exception {
        try {
            return executeNamedQueryList("getUserFriends", Friend.class, "userId", user.getId());
        } catch (NoResultException e) {
            return null;
        }
    }
}
