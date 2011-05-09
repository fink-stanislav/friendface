package net.friendface.friendface.service;

import net.friendface.friendface.model.providers.EntityManagerProviderStub;
import net.friendface.friendface.model.providers.RepositoryProviderStub;
import net.friendface.friendface.service.messages.MessagesService;
import net.friendface.friendface.service.user.UserService;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 5/1/11
 * Time: 3:43 PM
 */

public class FriendfaceServiceStub extends FriendfaceService {
    private static FriendfaceServiceStub service;
    private EntityManagerProviderStub entityManagerProviderStub;
    private RepositoryProviderStub repositoryProviderStub;

    public FriendfaceServiceStub() {
        entityManagerProviderStub = new EntityManagerProviderStub();
        repositoryProviderStub = new RepositoryProviderStub();
    }

    @Override
    public void start() throws Exception {
        entityManagerProviderStub.initialize();
        repositoryProviderStub.initialize();
    }

    @Override
    public UserService getUserService() throws RepositoryException {
        UserService service = UserService.getService();
        return service;
    }

    @Override
    public MessagesService getMessagesService() throws RepositoryException {
        MessagesService service = MessagesService.getService();
        return service;
    }

    @Override
    public void stop() {
        entityManagerProviderStub.close();
        repositoryProviderStub.close();
    }
}
