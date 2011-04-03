package com.exadel.friendface.model.dao.jcr;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.providers.RepositoryProvider;

import javax.jcr.*;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 7:40 PM
 */

public class JcrDAO {
    protected Repository repository;
    private Session session;

    public JcrDAO() {
        repository = RepositoryProvider.getInstance().getRepository();
        startSession();
    }

    public Session startSession() {
        try {
            session = repository.login(
                    new SimpleCredentials("username", "password".toCharArray())
            );
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return session;
    }

    public void endSession() {
        session.logout();
    }

    public void setupRepository(User user) {
        try {
            Node userNode = addUserNode(user);
            addNode(userNode, "albums");
            addNode(userNode, "videos");
            addNode(userNode, "posts");
            addNode(userNode, "messages");
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getRootNode() throws RepositoryException {
        return session.getRootNode();
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

    // in photo dao
    public Node getAlbumNode(User user, String albumTitle) throws RepositoryException {
        return getNode(getUserNode(user), "albums").getNode(albumTitle);
    }

    public Node addAlbumNode(User user, String albumTitle) throws RepositoryException {
        return addNode(getUserNode(user).getNode("albums"), albumTitle);
    }
}
