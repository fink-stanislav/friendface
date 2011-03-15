package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.enums.ContactState;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:12 PM
 */

public class JpaFriendsDAO extends JpaDAO implements FriendsDAO {
    public JpaFriendsDAO() {
        super();
    }

    public void setProposed(User sender, User receiver) throws Exception {
        Friend friend = new Friend();
        friend.setSender(sender);
        friend.setReceiver(receiver);
        friend.setApproved(false);
        persistEntity(friend);
    }

    public void approve(Friend friend) throws Exception {
        Friend f = getById(friend.getId());
        f.setApproved(true);
        persistEntity(f);
    }

    public void deleteFriend(Friend friend) throws Exception {
        removeEntity(friend);
    }

    public Friend getById(Integer recordId) throws Exception {
        try {
            return getById(recordId, Friend.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Friend getFriend(User receiver, User sender) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rec", receiver);
            params.put("sen", sender);
            return executeNamedQuery("getSingle", Friend.class, params);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Friend> getApproved(User user) throws Exception {
        return getFriendList(user, "getApproved");
    }

    public List<Friend> getProposed(User user) throws Exception {
        return getFriendList(user, "getProposal");
    }

    public List<Friend> getPending(User user) throws Exception {
        return getFriendList(user, "getPending");
    }

    private List<Friend> getFriendList(User user, String queryName) {
        try {
            return executeNamedQueryList(queryName, Friend.class, "user", user);
        } catch (NoResultException e) {
            return null;
        }
    }
}
