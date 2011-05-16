package net.friendface.friendface.model.dao.friends;

import net.friendface.friendface.model.dao.EntityDAO;
import net.friendface.friendface.model.dao.Operation;
import net.friendface.friendface.model.providers.RepositoryManager;
import net.friendface.friendface.model.entities.Friend;
import net.friendface.friendface.model.entities.User;

import javax.jcr.RepositoryException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 1:12 PM
 */

public class FriendDAOImpl extends EntityDAO implements FriendDAO {
    public FriendDAOImpl(EntityManager entityManager, RepositoryManager repositoryManager) {
        super(entityManager, repositoryManager);
    }

    public void setProposed(User sender, User receiver) {
        Friend friend = new Friend();
        friend.setSender(sender);
        friend.setReceiver(receiver);
        friend.setApproved(false);
        perform(new Operation<Friend>(friend) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
            }
        });
    }

    public void approve(Friend friend) {
        Friend f = getById(friend.getId());
        f.setApproved(true);
        perform(new Operation<Friend>(f) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.persist(entity);
            }
        });
    }

    public void deleteFriend(Friend friend) {
        perform(new Operation<Friend>(friend) {
            @Override
            public void perform() throws RepositoryException {
                entityManager.remove(entity);
            }
        });
    }

    public Friend getById(Integer recordId) {
        try {
            return getById(recordId, Friend.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public Friend getFriend(User receiver, User sender) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rec", receiver);
            params.put("sen", sender);
            return queryExecutor.executeNamedQuery("getSingle", Friend.class, params);
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Friend> getApproved(User user) {
        return getFriendList(user, "getApproved");
    }

    public List<Friend> getProposed(User user) {
        return getFriendList(user, "getProposal");
    }

    public List<Friend> getPending(User user) {
        return getFriendList(user, "getPending");
    }

    private List<Friend> getFriendList(User user, String queryName) {
        try {
            return queryExecutor.executeNamedQueryList(queryName, Friend.class, "user", user);
        } catch (NoResultException e) {
            return null;
        }
    }
}
