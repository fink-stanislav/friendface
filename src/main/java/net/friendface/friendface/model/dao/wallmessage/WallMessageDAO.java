package net.friendface.friendface.model.dao.wallmessage;

import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:14
 */

public interface WallMessageDAO {
    List<WallMessage> getMessages(User receiver);

    WallMessage getMessage(User receiver, Node messageNode) throws RepositoryException;

    void addMessage(WallMessage message, Node messageNode) throws RepositoryException;

    void removeMessage(WallMessage message, Node messageNode) throws RepositoryException;
}
