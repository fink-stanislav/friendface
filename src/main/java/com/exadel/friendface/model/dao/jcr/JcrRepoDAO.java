package com.exadel.friendface.model.dao.jcr;

import com.exadel.friendface.model.dao.RepoDAO;
import com.exadel.friendface.model.entities.User;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 9:55 PM
 */

public class JcrRepoDAO extends JcrDAO implements RepoDAO {
    public JcrRepoDAO() {
        super();
    }

    public void setupRepository(User user) throws RepositoryException {
        Node userNode = addUserNode(user);
        addNodeIfNotExists(userNode, "albums");
        addNodeIfNotExists(userNode, "videos");
        addNodeIfNotExists(userNode, "posts");
        addNodeIfNotExists(userNode, "messages");
    }

    private Node addNodeIfNotExists(Node parent, String name) throws RepositoryException {
        Node node;
        try {
            node = parent.getNode(name);
        } catch (PathNotFoundException e) {
            return parent.addNode(name);
        }
        return node;
    }

    public Node getUserNode(User user) throws RepositoryException {
        return getRootNode().getNode(user.getLoginEmail());
    }

    public Node getAlbumNode(User user, String albumTitle) throws RepositoryException {
        return getRootNode().getNode(user.getLoginEmail())
                .getNode("albums").getNode(albumTitle);
    }

    public Node addUserNode(User user) throws RepositoryException {
        return addNodeIfNotExists(getRootNode(), user.getLoginEmail());
    }

    public Node addAlbumNode(User user, String albumTitle) throws RepositoryException {
        return addNodeIfNotExists(getUserNode(user).getNode("albums"), albumTitle);
    }
}
