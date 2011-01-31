package com.exadel.friendface.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: sfink
 * Date: 1/31/11
 * Time: 4:43 PM
 */

public class ConnectionManager {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String SHORT_DB_URL = "jdbc:mysql://localhost/friendface";
    private static final String FULL_DB_URL = "jdbc:mysql://localhost/friendface?user=root&password=123";

    private static ConnectionManager instance = new ConnectionManager();
    private Connection connection;

    public static ConnectionManager getInstance() {
        return instance;
    }

    private ConnectionManager() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("jdbc driver loading error", e);
        }
    }

    public void initConnection() {
        try {
            connection = DriverManager.getConnection(FULL_DB_URL);
        } catch (SQLException e) {
            throw new RuntimeException("establishing connection error", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
