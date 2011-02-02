package com.exadel.friendface.model.connection;

import com.exadel.friendface.system.PropertyManager;
import com.exadel.friendface.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * User: sfink
 * Date: 1/31/11
 * Time: 4:43 PM
 */

public class ConnectionManager {

    private static ConnectionManager instance = new ConnectionManager();
    private Connection connection;

    private ConnectionManager() {
        try {
            PropertyManager appPropertyManager = new PropertyManager("application.properties");
            PropertyManager propertyManager =
                    new PropertyManager(appPropertyManager.getProperty("database.engine") + ".properties");

            Class.forName(propertyManager.getProperty("db.driver.name"));

            Map<String, String> params = new HashMap<String, String>();
            params.put("user", propertyManager.getProperty("db.user"));
            params.put("password", propertyManager.getProperty("db.password"));

            String url = StringUtils.buildUrl(propertyManager.getProperty("db.url"), params);
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
