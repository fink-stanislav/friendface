package com.exadel.friendface.model.dao.jpa;

import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.FriendsDAO;
import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.dao.WallMessageDAO;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:11 PM
 */

public class JpaDAOFactory extends DAOFactory {
    @Override
    public FriendsDAO getFriendsDAO() {
        return new JpaFriendsDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        throw new UnsupportedOperationException("Non-JPA operation");
    }

    @Override
    public WallMessageDAO getMessageDAO() {
        throw new UnsupportedOperationException("Non-JPA operation");
    }
}
