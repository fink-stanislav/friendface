package net.friendface.friendface.service.search;

import net.friendface.friendface.model.dao.DAOFactory;
import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.entities.Picture;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.Video;
import net.friendface.friendface.model.providers.EntityManagerProvider;
import net.friendface.friendface.model.queryhandling.SearchQueryExecutor;
import net.friendface.friendface.model.queryhandling.SearchQueryParams;
import net.friendface.friendface.view.beans.UserBean;

import java.util.Collections;
import java.util.List;

import static net.friendface.friendface.service.user.UserUtils.usersToUserBeans;

/**
 * Author: S. Fink
 * Date: 22.05.11
 * Time: 22:30
 */

public class SearchService {
    private static SearchService service;
    private FriendDAO friendDAO;

    public static SearchService getService() {
        if (service == null) {
            service = new SearchService();
        }
        return service;
    }

    private SearchService() {
        friendDAO = DAOFactory.getDAOFactory().getFriendsDAO();
    }

    public List<UserBean> searchForPeople(User currentUser, SearchQueryParams queryParams) {
        SearchQueryExecutor queryExecutor = new SearchQueryExecutor(
                EntityManagerProvider.getInstance().getFullTextEntityManager()
        );
        return usersToUserBeans(currentUser,
                queryExecutor.executeSearchQuery(User.class, queryParams), friendDAO);
    }

    public List<Picture> searchForPictures(SearchQueryParams queryParams) {
        SearchQueryExecutor queryExecutor = new SearchQueryExecutor(
                EntityManagerProvider.getInstance().getFullTextEntityManager()
        );
        return queryExecutor.executeSearchQuery(Picture.class, queryParams);
    }

    public List<Video> searchForVideos() {
        return Collections.emptyList();
    }
}
