package net.friendface.friendface.model.dao;

import net.friendface.friendface.model.providers.RepositoryManager;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Author: S. Fink
 * Date: 01.05.11
 * Time: 19:36
 */

public class JcrHelperStub extends RepositoryManager {
    public JcrHelperStub(Session session) throws RepositoryException {
        super(session);
    }
}