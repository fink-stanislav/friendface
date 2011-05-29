package net.friendface.friendface.service.friends;

import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.entities.Friend;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.enums.ContactState;

/**
 * Author: S. Fink
 * Date: 28.05.11
 * Time: 23:49
 */

public class FriendUtils {

    public static ContactState getContactState(User currentUser, User other, FriendDAO dao) {
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
}

