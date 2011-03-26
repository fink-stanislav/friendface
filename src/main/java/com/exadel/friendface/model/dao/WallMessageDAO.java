package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.entities.WallMessage;

/**
 * Author: S. Fink
 * Date: 26.03.11
 * Time: 20:14
 */

public interface WallMessageDAO {
    WallMessage getMessage(User sender, User receiver);

    WallMessage getMessage(Integer id);

    void addMessage(User sender, User receiver /*, binary parameter*/);

    void removeMessage(Integer id);
}
