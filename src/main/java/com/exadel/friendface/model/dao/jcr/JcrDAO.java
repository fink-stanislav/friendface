package com.exadel.friendface.model.dao.jcr;

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

    public Node getRootNode() throws RepositoryException {
        return session.getRootNode();
    }

    public void endSession() {
        session.logout();
    }
}
