package net.friendface.friendface.model.dao.messages;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.entities.Identifiable;
import net.friendface.friendface.model.entities.PrivateMessage;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.queryhandling.DefaultQueryParams;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 04.06.11
 * Time: 0:38
 */

public class PrivateMessageDAOImpl extends EntityDAO implements PrivateMessageDAO {
    public PrivateMessageDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    @Override
    public PrivateMessage getById(Integer id) throws RepositoryException {
        try {
            PrivateMessage message = getById(id, PrivateMessage.class);
            return repositoryManager.retrieveContent(message, getPath(message.getReceiver()));
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insertMessage(PrivateMessage message) {
        perform(new Operation<PrivateMessage>(message) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
                repositoryManager.storeContent(entity, getPath(entity.getReceiver()));
            }
        });
    }

    @Override
    public List<PrivateMessage> getUserMessages(User receiver, User sender) throws RepositoryException {
        try {
            DefaultQueryParams<User> queryParams = new DefaultQueryParams<User>("getPrivateMessages");
            queryParams.setParam("rec", receiver);
            queryParams.setParam("sen", sender);
            List<PrivateMessage> result =
                    queryExecutor.executeNamedQueryList(queryParams, PrivateMessage.class);
            return repositoryManager.retrieveContent(result, getPath(receiver));
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getConversations(User user) {
        try {
            DefaultQueryParams<User> queryParams = new DefaultQueryParams<User>("getSenders");
            queryParams.setParam("user", user);

            List<User> result = queryExecutor.executeNamedQueryList(queryParams, User.class);

            queryParams = new DefaultQueryParams<User>("getReceivers");
            queryParams.setParam("user", user);

            result.addAll(queryExecutor.executeNamedQueryList(queryParams, User.class));
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    public String getPath(Identifiable receiver) {
        return ((User) receiver).getLoginEmail() + "/messages/private";
    }
}
