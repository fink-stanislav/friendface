package net.friendface.friendface.model.dao.messages;

import net.friendface.friendface.model.entities.PrivateMessage;
import net.friendface.friendface.model.entities.User;

import javax.jcr.RepositoryException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 0:37
 */

public interface PrivateMessageDAO {
    PrivateMessage getById(Integer id) throws RepositoryException;

    void insertMessage(PrivateMessage message);

    List<PrivateMessage> getUserMessages(User receiver, User sender) throws RepositoryException;

    public List<User> getConversations(User user);
}
