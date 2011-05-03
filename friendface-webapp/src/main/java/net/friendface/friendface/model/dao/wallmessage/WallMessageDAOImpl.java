package net.friendface.friendface.model.dao.wallmessage;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:29
 */

public class WallMessageDAOImpl extends EntityDAO implements WallMessageDAO {
    public WallMessageDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    public WallMessage getById(Integer id) {
        try {
            return getById(id, WallMessage.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<WallMessage> getMessages(User receiver) throws RepositoryException {
        try {
            List<WallMessage> result =
                    queryExecutor.executeNamedQueryList("getMessageByUser", WallMessage.class, "rec", receiver);
            return retrieveContent(result, getPath(receiver));
        } catch (NoResultException e) {
            return null;
        }
    }

    public void addMessage(WallMessage message) throws RepositoryException {
        persistEntity(message);
        storeContent(message, getPath(message.getReceiver()));
    }

    public void removeMessage(WallMessage message) throws RepositoryException {
        removeContent(message, getPath(message.getReceiver()));
        removeEntity(message);
    }

    private String getPath(User receiver) {
        return receiver.getLoginEmail() + "/messages/public";
    }
}
