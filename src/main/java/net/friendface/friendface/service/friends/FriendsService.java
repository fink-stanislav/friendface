package net.friendface.friendface.service.friends;

import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.dao.DAOFactory;
import net.friendface.friendface.model.dao.friends.FriendsDAO;
import net.friendface.friendface.model.entities.Friend;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.enums.ContactState;
import net.friendface.friendface.service.user.UserService;

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
    private FriendsDAO dao;

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

    public void remove(SessionHelper session, Integer userToDelete) throws RepositoryException {
        User owner = UserService.getService().getFromSession(session);
        User friend = UserService.getService().getById(userToDelete);
        Friend result = getFriend(owner, friend);
        dao.deleteFriend(result);
    }

    public void sendProposal(SessionHelper session, Integer receiverId) throws RepositoryException {
        User sender = UserService.getService().getFromSession(session);
        User receiver = UserService.getService().getById(receiverId);
        dao.setProposed(sender, receiver);
    }

    public void approve(SessionHelper session, Integer userToApprove) throws RepositoryException {
        User owner = UserService.getService().getFromSession(session);
        User friend = UserService.getService().getById(userToApprove);
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

    public ContactState getContactState(User currentUser, User other) {
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
