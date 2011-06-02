package net.friendface.friendface.service;

import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.providers.RepositoryManagerProvider;
import net.friendface.friendface.service.friends.FriendsService;
import net.friendface.friendface.service.messages.MessagesService;
import net.friendface.friendface.service.pictures.PicturesService;
import net.friendface.friendface.service.search.SearchService;
import net.friendface.friendface.service.user.UserService;
import net.friendface.friendface.service.video.VideoService;

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
        RepositoryManagerProvider.getInstance().initialize();
    }

    public void stop() {
        EntityManagerProvider.getInstance().close();
        RepositoryManagerProvider.getInstance().close();
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

    public PicturesService getPicturesService() throws RepositoryException {
        return PicturesService.getService();
    }

    public SearchService getSearchService() {
        return SearchService.getService();
    }

    public VideoService getVideoService() {
        return VideoService.getService();
    }
}
