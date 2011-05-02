package net.friendface.friendface.controllers.actions.search.results;

import net.friendface.friendface.model.entities.User;

import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/10/11
 * Time: 10:20 PM
 */

public class PostsFound extends SearchResultAction {
    private List<User> resultList;

    public void performSearch(String searchEntry) throws Exception {
        setNotEmpty(false);
    }

    public List<User> getResultList() {
        return resultList;
    }
}
