package net.friendface.friendface.service;

import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.providers.RepositoryProvider;
import net.friendface.friendface.service.friends.FriendsService;
import net.friendface.friendface.service.messages.MessagesService;
import net.friendface.friendface.service.user.UserService;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:35 AM
 */
public class FriendfaceService {
    private static FriendfaceService service;

    public void start() throws Exception {
        EntityManagerProvider.getInstance().initialize();
        RepositoryProvider.getInstance().initialize();
    }

    public void stop() {
        EntityManagerProvider.getInstance().close();
        RepositoryProvider.getInstance().close();
    }

    public static FriendfaceService getService() {
        if (service == null) {
            service = new FriendfaceService();
        }
        return service;
    }

    public UserService getUserService() throws RepositoryException {
        return UserService.getService();
    }

    public FriendsService getFriendsService() {
        return FriendsService.getService();
    }

    public MessagesService getMessagesService() throws RepositoryException {
        return MessagesService.getService();
    }
}
