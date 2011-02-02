package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.connection.ConnectionManager;
import com.exadel.friendface.system.PropertyManager;

/**
 * User: sfink
 * Date: 2/2/11
 * Time: 1:08 PM
 */

public abstract class DAOFactory {

    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory() throws Exception {
        PropertyManager appPropertyManager = new PropertyManager("application.properties");
        PropertyManager propertyManager = new PropertyManager("daofactory.properties");
        String className = propertyManager.getProperty(appPropertyManager.getProperty("database.engine"));
        return (DAOFactory) Class.forName(className).newInstance();
    }
}
