package com.exadel.friendface.service.messages;

import com.exadel.friendface.controllers.actions.helpers.SessionHelper;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.WallMessageDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.entities.WallMessage;
import com.exadel.friendface.service.user.UserService;

import java.io.InputStream;
import java.util.List;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;

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
            dao = getDAOFactory(DAOFactory.StorageEngineType.mixed).getMessageDAO();
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

    public void removeWallMessage() {}

    public List<WallMessage> getWallMessages() {
        throw new UnsupportedOperationException();
    }
}
