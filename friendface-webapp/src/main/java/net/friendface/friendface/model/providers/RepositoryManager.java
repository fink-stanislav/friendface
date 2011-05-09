package net.friendface.friendface.model.providers;

import net.friendface.friendface.model.entities.User;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

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
