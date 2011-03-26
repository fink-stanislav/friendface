package com.exadel.friendface.model.dao.jpa;

import com.exadel.friendface.model.dao.WallMessageDAO;
import com.exadel.friendface.model.entities.Friend;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.entities.WallMessage;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:29
 */

public class JpaWallMessageDAO extends JpaDAO implements WallMessageDAO {
    public WallMessage getMessage(User sender, User receiver) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rec", receiver);
            params.put("sen", sender);
            return executeNamedQuery("getMessageByUsers", WallMessage.class, params);
        } catch (NoResultException e) {
            return null;
        }
    }

    public WallMessage getMessage(Integer id) {
        try {
            return getById(id, WallMessage.class);
        } catch (NoResultException e) {
            return null;
        }
    }

    public void addMessage(User sender, User receiver) {
        WallMessage message = new WallMessage();
        message.setReceiver(receiver);
        message.setSender(sender);
        persistEntity(message);
    }

    public void removeMessage(Integer id) {
        WallMessage message = getMessage(id);
        removeEntity(message);
    }
}
