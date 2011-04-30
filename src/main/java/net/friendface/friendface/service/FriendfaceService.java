package net.friendface.friendface.service;

import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.providers.RepositoryProvider;
import net.friendface.friendface.service.friends.FriendsService;
import net.friendface.friendface.service.messages.MessagesService;
import net.friendface.friendface.service.user.UserService;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:35 AM
 */
public class FriendfaceService {
    private static FriendfaceService service;

    private FriendfaceService() {
        EntityManagerProvider.getInstance();
        RepositoryProvider.getInstance();
    }

    public static void stop() {
        EntityManagerProvider.getInstance().close();
    }

    public static FriendfaceService getService() {
        if (service == null) {
            service = new FriendfaceService();
        }
        return service;
    }

    public UserService getUserService() {
        return UserService.getService();
    }

    public FriendsService getFriendsService() {
        return FriendsService.getService();
    }

    public MessagesService getMessagesService() {
        return MessagesService.getService();
    }
}
