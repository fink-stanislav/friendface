package com.exadel.friendface.model.connection;

import com.exadel.friendface.system.ApplicationPropertyManager;
import com.exadel.friendface.util.Pair;
import com.exadel.friendface.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.jar.Pack200;

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
            ApplicationPropertyManager apm = ApplicationPropertyManager.getInstance();
            Class.forName(apm.getProperty("db.driver.name"));
            String url = StringUtils.buildUrl(apm.getProperty("db.url"),
                    new Pair("user", apm.getProperty("db.user")),
                    new Pair("password", apm.getProperty("db.password")));
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
