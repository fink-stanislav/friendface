package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 10:36 PM
 */

public interface RepoDAO {
    public void setupRepository(User user) throws RepositoryException;

    public Node getUserNode(User user) throws RepositoryException;

    public Node getAlbumNode(User user, String albumTitle) throws RepositoryException;

    public Node addUserNode(User user) throws RepositoryException;

    public Node addAlbumNode(User user, String albumTitle) throws RepositoryException;
}
