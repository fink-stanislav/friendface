package net.friendface.friendface.service.friends;

import net.friendface.friendface.model.dao.DAOFactory;
import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.entities.Friend;
import net.friendface.friendface.model.entities.User;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 1:46 AM
 */

public class FriendsService {
    private static FriendsService service;
    private FriendDAO dao;

    public static FriendsService getService() {
        if (service == null) {
            service = new FriendsService();
        }
        return service;
    }

    public FriendsService() {
        dao = DAOFactory.getDAOFactory().getFriendsDAO();
    }

    public Friend getFriend(User owner, User friend) {
        Friend result = dao.getFriend(owner, friend);
        if (result == null) {
            result = dao.getFriend(friend, owner);
        }
        return result;
    }

    public Friend getFriend(Integer id) {
        return dao.getById(id);
    }

    public void remove(User owner, User friend) throws RepositoryException {
        Friend result = getFriend(owner, friend);
        dao.deleteFriend(result);
    }

    public void sendProposal(User sender, User receiver) throws RepositoryException {
        dao.setProposed(sender, receiver);
    }

    public void approve(User owner, User friend) throws RepositoryException {
        Friend result = getFriend(owner, friend);
        dao.approve(result);
    }

    public List<User> getApproved(User user) {
        List<Friend> friendList = dao.getApproved(user);
        return getUserList(user, friendList);
    }

    public List<User> getProposed(User user) {
        List<Friend> friendList = dao.getProposed(user);
        return getUserList(user, friendList);
    }

    public List<User> getPending(User user) {
        List<Friend> friendList = dao.getPending(user);
        return getUserList(user, friendList);
    }

    private List<User> getUserList(User user, List<Friend> friendList) {
        List<User> result = new ArrayList<User>(friendList.size());
        for (Friend friend : friendList) {
            if (friend.getReceiver().getId().equals(user.getId())) {
                result.add(friend.getSender());
            } else {
                result.add(friend.getReceiver());
            }
        }
        return result;
    }
}
