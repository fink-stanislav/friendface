package com.exadel.friendface.model.dao;

import com.exadel.friendface.system.PropertyManager;

/**
 * User: S. Fink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {

    public abstract UserDAO getUserDAO();
    public abstract FriendsDAO getFriendsDAO();

    private static PropertyManager classNames = new PropertyManager("application.properties");

    public enum StorageEngineType {
        jpa,
        filesystem
    }

    public static DAOFactory getDAOFactory() throws Exception {
        return getDAOFactory(DAOFactory.StorageEngineType.jpa);
    }

    public static DAOFactory getDAOFactory(StorageEngineType type) throws Exception {
        String className = classNames.getProperty(type.name());
        return (DAOFactory) Class.forName(className).newInstance();
    }
}
