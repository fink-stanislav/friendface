package com.exadel.friendface.service.friends;

import com.exadel.friendface.controllers.actions.helpers.SessionHelper;
import com.exadel.friendface.model.dao.FriendsDAO;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.enums.ContactState;
import com.exadel.friendface.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;
import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 1:46 AM
 */

public class FriendsService {
    private static FriendsService service;
    private FriendsDAO dao;

    public static FriendsService getService() {
        if (service == null) {
            service = new FriendsService();
        }
        return service;
    }

    public FriendsService() {
        try {
            dao = getDAOFactory().getFriendsDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Friend getFriend(User owner, User friend) throws Exception {
        Friend result = dao.getFriend(owner, friend);
        if (result == null) {
            result = dao.getFriend(friend, owner);
        }
        return result;
    }

    public Friend getFriend(Integer id) throws Exception {
        return dao.getById(id);
    }

    public void remove(SessionHelper session, Integer userToDelete) throws Exception {
        User owner = UserService.getService().getFromSession(session);
        User friend = UserService.getService().getById(userToDelete);
        Friend result = getFriend(owner, friend);
        dao.deleteFriend(result);
    }

    public void sendProposal(User sender, User receiver) throws Exception {
        dao.setProposed(sender, receiver);
    }

    public List<User> getApproved(User user) throws Exception {
        List<Friend> friendList = dao.getApproved(user);
        return getUserList(user, friendList);
    }

    public List<User> getProposed(User user) throws Exception {
        List<Friend> friendList = dao.getProposed(user);
        return getUserList(user, friendList);
    }

    public List<User> getPending(User user) throws Exception {
        List<Friend> friendList = dao.getPending(user);
        return getUserList(user, friendList);
    }

    public ContactState getContactState(User currentUser, User other) throws Exception {
        Friend friend = dao.getFriend(currentUser, other);
        if (friend == null) {
            friend = dao.getFriend(other, currentUser);
        } else {
            return friend.getApproved() ? ContactState.APPROVED : ContactState.PROPOSED;
        }

        if (friend == null) {
            return ContactState.NOT_CONNECTED;
        } else {
            return friend.getApproved() ? ContactState.APPROVED : ContactState.PENDING;
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
