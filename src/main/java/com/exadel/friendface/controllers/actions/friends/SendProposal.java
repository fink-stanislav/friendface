package com.exadel.friendface.controllers.actions.friends;

import com.exadel.friendface.controllers.actions.SessionAction;
import com.exadel.friendface.model.entities.User;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 3/12/11
 * Time: 1:58 AM
 */

public class SendProposal extends SessionAction {
    private User receiver;

    @Override
    public String execute() {
        try {
            sendProposal();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error"));
        }
    }

    private void sendProposal() throws Exception {
        User sender = getService().getUserService().getFromSession(getSession());
        getService().getFriendsService().sendProposal(sender, receiver);
    }
}
