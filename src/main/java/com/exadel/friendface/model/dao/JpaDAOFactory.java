package com.exadel.friendface.model.dao;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:11 PM
 */

public class JpaDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new JpaUserDAO();
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        return new JpaFriendsDAO();
    }
}
