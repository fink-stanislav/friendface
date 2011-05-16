package net.friendface.friendface.model.providers;

import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.SimpleCredentials;

/**
 * Author: S. Fink
 * Date: 5/1/11
 * Time: 3:38 PM
 */

public class RepositoryManagerProviderStub extends RepositoryManagerProvider {
    private String repositoryHome;
    private String repositoryConfig;

    public RepositoryManagerProviderStub() {
        super();
        repositoryHome = "C:\\friendface\\test-content\\";
        repositoryConfig = repositoryHome + "repository.xml";
    }

    @Override
    public void initialize() throws Exception {
        repository = new TransientRepository(repositoryConfig, repositoryHome);
        session = repository.login(
                new SimpleCredentials("username", "password".toCharArray())
        );
    }
}
