package net.friendface.friendface.service.messages;

import net.friendface.friendface.model.dao.wallmessage.WallMessageDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.utils.StringUtils;
import net.friendface.friendface.view.beans.WallMessageBean;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
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

    public static MessagesService getService() throws RepositoryException {
        if (service == null) {
            service = new MessagesService();
        }
        return service;
    }

    public WallMessage getById(Integer id) {
        return dao.getMessage(id);
    }

    private MessagesService() throws RepositoryException {
        dao = getDAOFactory().getMessageDAO();
    }

    public void postWallMessage(User sender, User receiver, String message) throws RepositoryException, IOException {
        WallMessage wallMessage = new WallMessage();
        wallMessage.setReceiver(receiver);
        wallMessage.setSender(sender);
        wallMessage.setContent(StringUtils.stringToBinary(message));
        dao.addMessage(wallMessage);
    }

    public void removeWallMessage(WallMessage message) throws RepositoryException {
        dao.removeMessage(message);
    }

    public List<WallMessageBean> getWallMessages(User receiver) throws Exception {
        List<WallMessage> wallMessageList = dao.getMessages(receiver);
        List<WallMessageBean> wallMessageBeans = new ArrayList<WallMessageBean>(wallMessageList.size());
        for (WallMessage message : wallMessageList) {
            WallMessageBean messageBean = new WallMessageBean(message);
            wallMessageBeans.add(messageBean);
        }
        return wallMessageBeans;
    }
}
