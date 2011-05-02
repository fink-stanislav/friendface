package net.friendface.friendface.controllers.actions.friends;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

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
    private SessionHelper sessionHelper;

    // something like active tab needed

    @Override
    public String execute() {
        try {
            User user = sessionHelper.getFromSession(getUserSessionKey());
            setApprovedFriends(user);
            setProposedFriends(user);
            setPendingFriends(user);
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void setApprovedFriends(User user) throws Exception {
        approvedFriends = FriendfaceService.getService().getFriendsService().getApproved(user);
        hasApprovedFriends = !approvedFriends.isEmpty();
    }

    private void setProposedFriends(User user) throws Exception {
        proposedFriends = FriendfaceService.getService().getFriendsService().getProposed(user);
        hasProposedFriends = !proposedFriends.isEmpty();
    }

    private void setPendingFriends(User user) throws Exception {
        pendingFriends = FriendfaceService.getService().getFriendsService().getPending(user);
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
        sessionHelper = new SessionHelper(session);
    }
}
