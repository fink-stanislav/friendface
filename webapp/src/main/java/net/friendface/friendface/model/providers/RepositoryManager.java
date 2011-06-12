package net.friendface.friendface.model.providers;

import net.friendface.friendface.model.entities.ContentEntity;
import net.friendface.friendface.model.entities.User;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 7:40 PM
 */

public class RepositoryManager {
    private Session session;

    public RepositoryManager(Session session) throws RepositoryException {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setupRepository(User user) throws RepositoryException {
        Node userNode = addUserNode(user);
        addNode(userNode, "albums");
        addNode(userNode, "videos");
        addNode(userNode, "posts");
        Node messagesNode = addNode(userNode, "messages");
        addNode(messagesNode, "public");
        addNode(messagesNode, "private");
        session.save();
    }

    public void removeRepository(User user) throws RepositoryException {
        Node userNode = getUserNode(user);
        userNode.remove();
        session.save();
    }

    public Node getRootNode() throws RepositoryException {
        return session.getRootNode();
    }

    public Node getNode(String path) throws RepositoryException {
        return session.getRootNode().getNode(path);
    }

    public void removeNode(String path) throws RepositoryException {
        session.removeItem(path);
    }

    public <T extends ContentEntity> T retrieveContent(T entity, String path) throws RepositoryException {
        Node node = getNode(path);
        Property property = node.getProperty(
                Integer.toString(entity.getId())
        );
        entity.setContent(property.getBinary());
        return entity;
    }

    public <T extends ContentEntity> List<T> retrieveContent(List<T> entityList, String path) throws RepositoryException {
        Node node = getNode(path);
        for (T entity : entityList) {
            Property property = node.getProperty(
                    Integer.toString(entity.getId())
            );
            entity.setContent(property.getBinary());
        }
        return entityList;
    }

    public <T extends ContentEntity> void storeContent(T entity, String path) throws RepositoryException {
        Node node = getNode(path);
        String name = Integer.toString(entity.getId());
        node.setProperty(name, entity.getContent());
        getSession().save();
    }

    public <T extends ContentEntity> void removeContent(T entity, String path) {
        try {
            Node node = getNode(path);
            Property property = node.getProperty(
                    Integer.toString(entity.getId())
            );
            property.remove();
            getSession().save();
        } catch (RepositoryException e) {
            // do nothing
        }
    }

    public Node getNode(Node parent, String name) throws RepositoryException {
        return parent.getNode(name);
    }

    public Node addNode(Node parent, String name) throws RepositoryException {
        return parent.addNode(name);
    }

    public Node getUserNode(User user) throws RepositoryException {
        return getNode(getRootNode(), user.getLoginEmail());
    }

    public Node addUserNode(User user) throws RepositoryException {
        return addNode(getRootNode(), user.getLoginEmail());
    }
}
