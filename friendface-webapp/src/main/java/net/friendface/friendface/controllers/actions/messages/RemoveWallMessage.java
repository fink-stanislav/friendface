package net.friendface.friendface.controllers.actions.messages;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.messages.MessagesService;

import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 4/3/11
 * Time: 9:13 PM
 */

public class RemoveWallMessage extends StandardAction {
    private Integer id;

    @Override
    public String execute() {
        try {
            MessagesService service = FriendfaceService.getService().getMessagesService();
            WallMessage message = service.getById(id);
            service.removeWallMessage(message);
        } catch (RepositoryException e) {
            return ERROR;
        }
        return SUCCESS;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
