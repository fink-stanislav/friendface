package com.exadel.friendface.service;

import com.exadel.friendface.model.providers.EntityManagerProvider;
import com.exadel.friendface.service.friends.FriendsService;
import com.exadel.friendface.service.user.UserService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;

/**
 * Author: S. Fink
 * Date: 3/7/11
 * Time: 12:35 AM
 */
public class FriendfaceService {
    private static FriendfaceService service;

    private FriendfaceService() {
        try {
            EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
            //RepositoryProvider.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
