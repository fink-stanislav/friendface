package com.exadel.friendface.model.connection;

import com.exadel.friendface.system.PropertyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import static com.exadel.friendface.util.StringUtils.buildUrl;

/**
 * User: S. Fink
 * Date: 1/31/11
 * Time: 4:43 PM
 */

public class ConnectionManager {

    private static ConnectionManager instance = new ConnectionManager();
    private Connection connection;

    private PropertyManager getDatabaseProperties() {
        PropertyManager propertyManager = new PropertyManager("application.properties");
        String databaseEngineName = propertyManager.getProperty("database.engine");
        return new PropertyManager(databaseEngineName + ".properties");
    }

    private ConnectionManager() {
        try {
            PropertyManager dbProperties = getDatabaseProperties();
            Class.forName(dbProperties.getProperty("db.driver.name"));

            Map<String, String> params = new HashMap<String, String>();
            params.put("user", dbProperties.getProperty("db.user"));
            params.put("password", dbProperties.getProperty("db.password"));

            String url = buildUrl(dbProperties.getProperty("db.url"), params);
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
