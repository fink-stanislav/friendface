package com.exadel.friendface.model.dao.jpa;

import com.exadel.friendface.model.dao.DAOFactoryImpl;
import com.exadel.friendface.model.dao.FriendsDAO;
import com.exadel.friendface.model.dao.UserDAO;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:11 PM
 */

public class JpaDAOFactory extends DAOFactoryImpl {
    @Override
    public UserDAO getUserDAO() {
        return new JpaUserDAO();
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        return new JpaFriendsDAO();
    }
}
