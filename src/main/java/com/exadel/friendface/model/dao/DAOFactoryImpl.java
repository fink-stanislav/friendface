package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.dao.friends.FriendsDAO;
import com.exadel.friendface.model.dao.friends.FriendsDAOImpl;
import com.exadel.friendface.model.dao.user.UserDAO;
import com.exadel.friendface.model.dao.user.UserDAOImpl;
import com.exadel.friendface.model.dao.wallmessage.WallMessageDAO;
import com.exadel.friendface.model.dao.wallmessage.WallMessageDAOImpl;

/**
 * Author: S. Fink
 * Date: 4/19/11
 * Time: 9:01 PM
 */

public class DAOFactoryImpl extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        return new FriendsDAOImpl();
    }

    @Override
    public WallMessageDAO getMessageDAO() {
        return new WallMessageDAOImpl();
    }
}
