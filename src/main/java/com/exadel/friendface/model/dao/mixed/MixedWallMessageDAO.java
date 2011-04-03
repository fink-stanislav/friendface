package com.exadel.friendface.model.dao.mixed;

import com.exadel.friendface.model.dao.WallMessageDAO;
import com.exadel.friendface.model.dao.jcr.JcrDAO;
import com.exadel.friendface.model.dao.jpa.JpaDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.entities.WallMessage;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:29
 */

public class MixedWallMessageDAO implements WallMessageDAO {
    private JpaDAO jpaDAO;
    private JcrDAO jcrDAO;

    public MixedWallMessageDAO() {
        jpaDAO = new JpaDAO();
        jcrDAO = new JcrDAO();
    }

    public WallMessage getMessage(User sender, User receiver) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rec", receiver);
            params.put("sen", sender);
            return getMessage(
                    jpaDAO.executeNamedQuery("getMessageByUsers", WallMessage.class, params)
            );
        } catch (NoResultException e) {
            return null;
        } catch (RepositoryException e) {
            return null;
        }
    }

    public WallMessage getMessage(Integer id) {
        try {
            return getMessage(
                    jpaDAO.getById(id, WallMessage.class)
            );
        } catch (NoResultException e) {
            return null;
        } catch (RepositoryException e) {
            return null;
        }
    }

    public void addMessage(User sender, User receiver, Binary message) throws RepositoryException {
        WallMessage msg = new WallMessage();
        msg.setReceiver(receiver);
        msg.setSender(sender);
        msg.setMessage(message);
        jpaDAO.persistEntity(msg);
        addMessage(msg);
    }

    public void removeMessage(Integer id) {
        WallMessage message = getMessage(id);
        jpaDAO.removeEntity(message);
    }

    private WallMessage getMessage(WallMessage message) throws RepositoryException {
        Node messages = jcrDAO.getNode(jcrDAO.getUserNode(message.getReceiver()), "messages");
        Node messageNode = messages.getNode(Integer.toString(message.getId()));
        message.setMessage(messageNode.getProperty("message").getBinary());
        return message;
    }

    private void addMessage(WallMessage message) throws RepositoryException {
        Node messages = jcrDAO.getNode(jcrDAO.getUserNode(message.getReceiver()), "messages");
        Node messageNode = messages.addNode(Integer.toString(message.getId()));
        messageNode.setProperty("message", message.getMessage());
    }
}
