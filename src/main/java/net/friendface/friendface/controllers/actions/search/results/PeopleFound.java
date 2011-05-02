package net.friendface.friendface.controllers.actions.search.results;

import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.view.beans.DisplayUserBean;

import java.util.ArrayList;
import java.util.List;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * Author: S. Fink
 * Date: 3/10/11
 * Time: 10:20 PM
 */

public class PeopleFound extends SearchResultAction {
    private List<DisplayUserBean> resultList;

    @Override
    public void performSearch(String searchEntry) throws Exception {
        if (searchEntry.equals("People")) {
            List<User> userList = FriendfaceService.getService().getUserService().find(parameters.getDefaultParameters());
            prepare(userList);
            setNotEmpty(!resultList.isEmpty());
            return;
        }
        setNotEmpty(false);
    }

    public void prepare(List<User> userList) throws Exception {
        resultList = new ArrayList<DisplayUserBean>(userList.size());
        User currentUser = sessionHelper.getFromSession(getUserSessionKey());
        for (User user : userList) {
            if (currentUser.getId().equals(user.getId())) {
                continue;
            }
            resultList.add(new DisplayUserBean(
                    user, FriendfaceService.getService().getFriendsService().getContactState(currentUser, user))
            );
        }
    }

    public List<DisplayUserBean> getResultList() {
        return resultList;
    }
}
