package com.exadel.friendface.controllers.actions.search.results;

import com.exadel.friendface.model.entities.User;

import java.util.List;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 3/10/11
 * Time: 10:20 PM
 */

public class PeopleFound extends SearchResultAction {
    private List<User> resultList;

    @Override
    public void performSearch(String searchEntry) throws Exception {
        if (searchEntry.equals("People")) {
            resultList = getService().getUserService().find(getDefaultParameters());
            setNotEmpty(!resultList.isEmpty());
            return;
        }
        setNotEmpty(false);
    }

    public List<User> getResultList() {
        return resultList;
    }
}
