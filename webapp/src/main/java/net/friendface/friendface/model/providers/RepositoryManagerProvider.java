package net.friendface.friendface.model.providers;

import org.apache.jackrabbit.core.RepositoryImpl;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Author: S. Fink
 * Date: 3/6/11
 * Time: 1:22 AM
 */

public class RepositoryManagerProvider {
    private static RepositoryManagerProvider instance;
    protected Repository repository;
    protected Session session;
    protected RepositoryManager repositoryManager;

    public void initialize() throws Exception {
        InitialContext initialContext = new InitialContext();
        Context localContext = (Context) initialContext.lookup("java:comp/env");
        Object repository = localContext.lookup("jcr/repository");
        if (repository instanceof Repository) {
            this.repository = (Repository) repository;
            startSession();
            repositoryManager = new RepositoryManager(session);
        }
    }

    protected void startSession() throws RepositoryException {
        session = repository.login(
                new SimpleCredentials("username", "password".toCharArray())
        );
    }

    public static RepositoryManagerProvider getInstance() {
        try {
            if (instance == null) {
                instance = new RepositoryManagerProvider();
            }
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

    public Session getSession() {
        return session;
    }

    public RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }

    public void close() {
        session.logout();
        ((RepositoryImpl) repository).shutdown();
    }
}
