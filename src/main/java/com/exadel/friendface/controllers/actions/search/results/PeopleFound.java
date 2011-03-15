package com.exadel.friendface.controllers.actions.search.results;

import com.exadel.friendface.controllers.actions.utils.ParameterUtils;
import com.exadel.friendface.controllers.actions.utils.SessionUtils;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.view.beans.DisplayUserBean;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

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
            List<User> userList = getService().getUserService().find(parameters.getDefaultParameters());            
            prepare(userList);
            setNotEmpty(!resultList.isEmpty());
            return;
        }
        setNotEmpty(false);
    }

    public void prepare(List<User> userList) throws Exception {
        resultList = new ArrayList<DisplayUserBean>(userList.size());
        User currentUser = getService().getUserService().getFromSession(session.getSession());
        for (User user : userList) {
            if (currentUser.getId().equals(user.getId())) {
                continue;
            }
            resultList.add(new DisplayUserBean(
                    user, getService().getFriendsService().getContactState(currentUser, user))
            );
        }
    }

    public List<DisplayUserBean> getResultList() {
        return resultList;
    }
}
