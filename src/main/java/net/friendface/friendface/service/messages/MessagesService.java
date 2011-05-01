package net.friendface.friendface.service.messages;

import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.model.dao.JcrHelper;
import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.service.user.UserService;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.List;

import static net.friendface.friendface.model.dao.DAOFactory.getDAOFactory;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 10:32 PM
 */

public class MessagesService {
    private static MessagesService service;
    private WallMessageDAO dao;
    private JcrHelper jcrHelper;

    public static MessagesService getService() throws RepositoryException {
        if (service == null) {
            service = new MessagesService();
        }
        return service;
    }

    private MessagesService() throws RepositoryException {
        dao = getDAOFactory().getMessageDAO();
    }

    public void setJcrHelper(JcrHelper jcrHelper) {
        this.jcrHelper = jcrHelper;
    }

    public void postWallMessage(SessionHelper session, User sender, String message) throws RepositoryException {
        User receiver = UserService.getService().getFromSession(session);
        Node messageNode = jcrHelper.getNode(
                jcrHelper.getUserNode(receiver), "messages/public"
        );
        WallMessage wallMessage = new WallMessage();
        wallMessage.setReceiver(receiver);
        wallMessage.setSender(sender);
    }

    public void removeWallMessage(SessionHelper session) throws RepositoryException {
        User receiver = UserService.getService().getFromSession(session);
        Node messageNode = jcrHelper.getNode(
                jcrHelper.getUserNode(receiver), "messages/public"
        );
    }

    public List<WallMessage> getWallMessages() {
        throw new UnsupportedOperationException();
    }
}
