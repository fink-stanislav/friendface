package net.friendface.friendface.service.messages;

import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.dao.DAOFactory;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.service.user.UserService;

import java.io.InputStream;
import java.util.List;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 10:32 PM
 */

public class MessagesService {
    private static MessagesService service;
    private WallMessageDAO dao;

    private MessagesService() {
        try {
            dao = DAOFactory.getDAOFactory().getMessageDAO();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MessagesService getService() {
        if (service == null) {
            service = new MessagesService();
        }
        return service;
    }

    public void postWallMessage(SessionHelper session, User sender, InputStream stream) {
        User receiver = UserService.getService().getFromSession(session);
    }

    public void removeWallMessage() {
    }

    public List<WallMessage> getWallMessages() {
        throw new UnsupportedOperationException();
    }
}
