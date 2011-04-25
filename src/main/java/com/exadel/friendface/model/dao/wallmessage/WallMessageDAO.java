package com.exadel.friendface.model.dao.wallmessage;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.entities.WallMessage;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:14
 */

public interface WallMessageDAO {
    WallMessage getMessage(User sender, User receiver);

    WallMessage getMessage(Integer id);

    void addMessage(User sender, User receiver, Binary message) throws RepositoryException;

    void removeMessage(Integer id);
}
