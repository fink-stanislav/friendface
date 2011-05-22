package net.friendface.friendface.model.dao.wallmessage;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.ExecutorParams;

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

    public void addMessage(WallMessage message) throws RepositoryException {
        perform(new Operation<WallMessage>(message) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
                repositoryManager.storeContent(entity, getPath(entity.getReceiver()));
            }
        });
    }

    public void removeMessage(WallMessage message) throws RepositoryException {
        perform(new Operation<WallMessage>(message) {
            @Override
            public void perform() throws RepositoryException {
                repositoryManager.removeContent(entity, getPath(entity.getReceiver()));
                entityManager.remove(entity);
            }
        });
    }

    public WallMessage getById(Integer id) throws RepositoryException {
        try {
            WallMessage wallMessage = getById(id, WallMessage.class);
            return repositoryManager.retrieveContent(wallMessage, getPath(wallMessage.getReceiver()));
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<WallMessage> getMessages(User receiver) throws RepositoryException {
        try {
            ExecutorParams params = new ExecutorParams("getMessageByUser");
            params.setParam("rec", receiver);
            List<WallMessage> result =
                    queryExecutor.executeNamedQueryList(params, WallMessage.class);
            return repositoryManager.retrieveContent(result, getPath(receiver));
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getPath(Identifiable receiver) {
        return ((User) receiver).getLoginEmail() + "/messages/public";
    }
}
