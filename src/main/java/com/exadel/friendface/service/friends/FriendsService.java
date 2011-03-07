package com.exadel.friendface.service.friends;

import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;

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

    public void remove(Friend friend) throws Exception {
        getDAOFactory().getFriendsDAO().deleteFriend(friend);
    }

    public void add(User user) {}

    public Friend getById(Integer id) throws Exception {
        return getDAOFactory().getFriendsDAO().getFriend(id);
    }

    public List<Friend> getFriends(User user) throws Exception {
        return getDAOFactory().getFriendsDAO().getFriends(user);
    }
}
