package net.friendface.deployer;

import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

/**
 * Author: S. Fink
 * Date: 4/30/11
 * Time: 8:44 PM
 */

public class RepositoryHandler {
    private String repositoryHome;
    private String repositoryConfig;
    private Session session;

    public RepositoryHandler() {
        repositoryHome = "C:\\friendface\\content\\";
        repositoryConfig = repositoryHome + "repository.xml";
    }

    public RepositoryHandler(String repositoryHome, String repositoryConfig) {
        this.repositoryHome = repositoryHome;
        this.repositoryConfig = repositoryConfig;
    }

    public Session createSession() throws RepositoryException {
        Repository repository = new TransientRepository(repositoryConfig, repositoryHome);
        return session = repository.login(new SimpleCredentials("admin", "testPassword".toCharArray()));
    }

    public void destroySession() {
        session.logout();
    }
}
