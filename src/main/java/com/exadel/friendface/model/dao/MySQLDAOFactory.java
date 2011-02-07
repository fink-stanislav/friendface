package com.exadel.friendface.model.dao;

/**
 * User: sfink
 * Date: 2/2/11
 * Time: 1:11 PM
 */

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO();
    }

    @Override
    public FriendsDAO getFriendsDAO() {
        return new MySQLFriendsDAO();
    }
}
