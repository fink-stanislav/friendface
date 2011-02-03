package com.exadel.friendface.model.dao;

import com.exadel.friendface.system.PropertyManager;

/**
 * User: sfink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {

    public abstract UserDAO getUserDAO();

    private static PropertyManager classNames = new PropertyManager("application.properties");

    public enum StorageEngineType {
        mysql,
        filesystem
    }

    public static DAOFactory getDAOFactory(StorageEngineType type) throws Exception {
        String className = classNames.getProperty(type.name());
        return (DAOFactory) Class.forName(className).newInstance();
    }
}
