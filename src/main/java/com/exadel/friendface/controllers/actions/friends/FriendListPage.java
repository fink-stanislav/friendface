package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.actions.utils.SessionUtils;
import com.exadel.friendface.model.entities.User;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 2/7/11
 * Time: 2:24 PM
 */

public class FriendListPage extends StandardAction implements SessionAware {
    private List<User> approvedFriends;
    private List<User> proposedFriends;
    private List<User> pendingFriends;
    private Boolean hasApprovedFriends;
    private Boolean hasProposedFriends;
    private Boolean hasPendingFriends;
    private SessionUtils session;

    // something like active tab needed

    @Override
    public String execute() {
        try {
            User user = getService().getUserService().getFromSession(session.getSession());
            setApprovedFriends(user);
            setProposedFriends(user);
            setPendingFriends(user);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void setApprovedFriends(User user) throws Exception {
        approvedFriends = getService().getFriendsService().getApproved(user);
        hasApprovedFriends = !approvedFriends.isEmpty();
    }

    private void setProposedFriends(User user) throws Exception {
        proposedFriends = getService().getFriendsService().getProposed(user);
        hasProposedFriends = !proposedFriends.isEmpty();
    }

    private void setPendingFriends(User user) throws Exception {
        pendingFriends = getService().getFriendsService().getPending(user);
        hasPendingFriends = !pendingFriends.isEmpty();
    }

    public Boolean getHasApprovedFriends() {
        return hasApprovedFriends;
    }

    public Boolean getHasProposedFriends() {
        return hasProposedFriends;
    }

    public Boolean getHasPendingFriends() {
        return hasPendingFriends;
    }

    public List<User> getApprovedFriends() {
        return approvedFriends;
    }

    public List<User> getProposedFriends() {
        return proposedFriends;
    }

    public List<User> getPendingFriends() {
        return pendingFriends;
    }

    public void setSession(Map session) {
        this.session = new SessionUtils(session);
    }
}
