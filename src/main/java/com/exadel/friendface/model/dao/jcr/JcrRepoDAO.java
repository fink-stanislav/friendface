package com.exadel.friendface.model.dao.jcr;

import com.exadel.friendface.model.dao.RepoDAO;
import com.exadel.friendface.model.entities.User;

import javax.jcr.*;

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
        getNode(userNode, "albums");
        getNode(userNode, "videos");
        getNode(userNode, "posts");
        getNode(userNode, "messages");
    }

    private Node getNode(Node parent, String name) throws RepositoryException {
        try {
            return parent.getNode(name);
        } catch (PathNotFoundException e) {
            return null;
        }
    }

    private Node addNode(Node parent, String name) throws RepositoryException {
        return parent.addNode(name);
    }

    public Node getUserNode(User user) throws RepositoryException {
        Node root = getRootNode();
        String name = user.getLoginEmail();
        return getNode(root, name);
    }

    public Node getAlbumNode(User user, String albumTitle) throws RepositoryException {
        Node albums = getUserNode(user).getNode("albums");
        return getNode(albums, albumTitle);
    }

    public Node addUserNode(User user) throws RepositoryException {
        return addNode(getRootNode(), user.getLoginEmail());
    }

    public Node addAlbumNode(User user, String albumTitle) throws RepositoryException {
        return addNode(getUserNode(user).getNode("albums"), albumTitle);
    }

    public void addPicture(User user, String albumTitle, Binary picture) throws RepositoryException {
        Node node = getAlbumNode(user, albumTitle);
        node.setProperty("randomname", picture);
    }
}
