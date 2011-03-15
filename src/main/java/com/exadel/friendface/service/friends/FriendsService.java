package com.exadel.friendface.service.friends;

import com.exadel.friendface.model.dao.FriendsDAO;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.enums.ContactState;

import java.util.ArrayList;
import java.util.List;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 1:46 AM
 */

public class FriendsService {
    private static FriendsService service;

    public static FriendsService getService() {
        if (service == null) {
            service = new FriendsService();
        }
        return service;
    }

    public Friend getFriend(User owner, User friend) throws Exception {
        FriendsDAO friendsDAO = getDAOFactory().getFriendsDAO();
        Friend result = friendsDAO.getFriend(owner, friend);
        if (result == null) {
            result = friendsDAO.getFriend(friend, owner);
        }
        return result;
    }

    public void remove(Friend friend) throws Exception {
        getDAOFactory().getFriendsDAO().deleteFriend(friend);
    }

    public void sendProposal(User sender, User receiver) throws Exception {
        getDAOFactory().getFriendsDAO().setProposed(sender, receiver);
    }

    public Friend getById(Integer id) throws Exception {
        return getDAOFactory().getFriendsDAO().getById(id);
    }

    public List<User> getApproved(User user) throws Exception {
        List<Friend> friendList = getDAOFactory().getFriendsDAO().getApproved(user);
        return getUserList(user, friendList);
    }

    public List<User> getProposed(User user) throws Exception {
        List<Friend> friendList = getDAOFactory().getFriendsDAO().getProposed(user);
        return getUserList(user, friendList);
    }

    public List<User> getPending(User user) throws Exception {
        List<Friend> friendList = getDAOFactory().getFriendsDAO().getPending(user);
        return getUserList(user, friendList);
    }

    public ContactState getContactState(User currentUser, User other) throws Exception {
        Friend friend = getDAOFactory().getFriendsDAO().getFriend(currentUser, other);
        if (friend == null) {
            friend = getDAOFactory().getFriendsDAO().getFriend(other, currentUser);
        }
        if (friend == null) {
            return ContactState.NOT_CONNECTED;
        } else if (friend.getApproved()) {
            return ContactState.APPROVED;
        } else {
            return ContactState.PENDING;
        }
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
