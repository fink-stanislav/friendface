package com.exadel.friendface.service;

import com.exadel.friendface.model.providers.EntityManagerProvider;
import com.exadel.friendface.service.friends.FriendsService;
import com.exadel.friendface.service.user.UserService;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:35 AM
 */
public class FriendfaceService {
    private static FriendfaceService service;

    private FriendfaceService() {
        EntityManagerProvider.getInstance();
        //RepositoryProvider.getInstance();
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
}
