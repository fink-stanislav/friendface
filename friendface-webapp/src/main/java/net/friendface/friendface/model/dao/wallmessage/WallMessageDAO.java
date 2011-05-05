package net.friendface.friendface.model.dao.wallmessage;

import net.friendface.friendface.model.dao.ContentDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;

import javax.jcr.RepositoryException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:14
 */

public interface WallMessageDAO extends ContentDAO {
    List<WallMessage> getMessages(User receiver) throws RepositoryException;

    WallMessage getById(Integer id) throws RepositoryException;

    void addMessage(WallMessage message) throws RepositoryException;

    void removeMessage(WallMessage message) throws RepositoryException;
}
