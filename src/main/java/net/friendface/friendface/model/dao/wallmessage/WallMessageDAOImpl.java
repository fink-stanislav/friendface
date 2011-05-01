package net.friendface.friendface.model.dao.wallmessage;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.model.providers.EntityManagerProvider;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:29
 */

public class WallMessageDAOImpl extends EntityDAO implements WallMessageDAO {
    public WallMessageDAOImpl(EntityManagerProvider provider) {
        super(provider);
    }

    public WallMessage getMessage(User receiver, Node messageNode) throws RepositoryException {
        try {
            WallMessage result =
                    getQueryExecutor().executeNamedQuery("getMessageByUser", WallMessage.class, "rec", receiver);
            return retrieveContent(result, messageNode);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<WallMessage> getMessages(User receiver) {
        return null;
    }

    public void addMessage(WallMessage message, Node messageNode) throws RepositoryException {
        persistEntity(message);
        storeContent(message, messageNode);
    }

    public void removeMessage(WallMessage message, Node messageNode) throws RepositoryException {
        removeContent(message, messageNode);
        removeEntity(message);
    }
}
