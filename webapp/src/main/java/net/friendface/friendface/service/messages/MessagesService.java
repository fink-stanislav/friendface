package net.friendface.friendface.service.messages;

import net.friendface.friendface.model.dao.messages.PrivateMessageDAO;
import net.friendface.friendface.model.dao.messages.WallMessageDAO;
import net.friendface.friendface.model.entities.PrivateMessage;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.entities.WallMessage;
import net.friendface.friendface.utils.StringUtils;
import net.friendface.friendface.view.beans.PrivateMessageBean;
import net.friendface.friendface.view.beans.WallMessageBean;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static net.friendface.friendface.model.dao.DAOFactory.getDAOFactory;

/**
 * Author: S. Fink
 * Date: 3/25/11
 * Time: 10:32 PM
 */

public class MessagesService {
    private static MessagesService service;
    private WallMessageDAO wallMessageDAO;
    private PrivateMessageDAO privateMessageDAO;

    public static MessagesService getService() throws RepositoryException {
        if (service == null) {
            service = new MessagesService();
        }
        return service;
    }

    public WallMessage getById(Integer id) throws RepositoryException {
        return wallMessageDAO.getById(id);
    }

    private MessagesService() throws RepositoryException {
        wallMessageDAO = getDAOFactory().getMessageDAO();
        privateMessageDAO = getDAOFactory().getPrivateMessageDAO();
    }

    public void postWallMessage(User sender, User receiver, String message) throws RepositoryException, IOException {
        WallMessage wallMessage = new WallMessage();
        wallMessage.setReceiver(receiver);
        wallMessage.setSender(sender);
        wallMessage.setContent(StringUtils.stringToBinary(message));
        wallMessageDAO.addMessage(wallMessage);
    }

    public void removeWallMessage(WallMessage message) throws RepositoryException {
        wallMessageDAO.removeMessage(message);
    }

    public List<WallMessageBean> getWallMessages(User receiver) throws Exception {
        List<WallMessage> wallMessageList = wallMessageDAO.getMessages(receiver);
        List<WallMessageBean> wallMessageBeans = new ArrayList<WallMessageBean>(wallMessageList.size());
        for (WallMessage message : wallMessageList) {
            WallMessageBean messageBean = new WallMessageBean(message);
            wallMessageBeans.add(messageBean);
        }
        return wallMessageBeans;
    }

    public void postPrivateMessage(User sender, User receiver, String message) throws IOException {
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setSender(sender);
        privateMessage.setReceiver(receiver);
        privateMessage.setPostDate(new Date());
        privateMessage.setContent(StringUtils.stringToBinary(message));
        privateMessageDAO.insertMessage(privateMessage);
    }

    public List<User> getConversations(User user) {
        List<User> userList = privateMessageDAO.getConversations(user);
        List<User> result = new ArrayList<User>(userList.size());

        if (userList.size() > 0) {
            result.add(userList.get(0));
        }

        for (User u : userList) {
            for (int i = 0; i < result.size(); i++) {
                if (!u.equals(result.get(i))) {
                    result.add(u);
                    break;
                }
            }
        }
        return result;
    }

    public List<PrivateMessageBean<PrivateMessage>> getPrivateMessages(User receiver, User sender) throws RepositoryException {
        List<PrivateMessage> messagesTo = privateMessageDAO.getUserMessages(receiver, sender);
        List<PrivateMessage> messagesFrom = privateMessageDAO.getUserMessages(sender, receiver);
        List<PrivateMessageBean<PrivateMessage>> beans = new ArrayList<PrivateMessageBean<PrivateMessage>>(messagesTo.size() + messagesFrom.size());

        for (PrivateMessage message : messagesTo) {
            PrivateMessageBean<PrivateMessage> bean = new PrivateMessageBean<PrivateMessage>(message);
            beans.add(bean);
        }
        for (PrivateMessage message : messagesFrom) {
            PrivateMessageBean<PrivateMessage> bean = new PrivateMessageBean<PrivateMessage>(message);
            beans.add(bean);
        }
        Collections.sort(beans);
        return beans;
    }
}
