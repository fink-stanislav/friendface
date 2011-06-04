package net.friendface.deployer;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static net.friendface.deployer.PropertyManager.getPropertyManager;

/**
 * Populates database with data from specified sql-script.
 * Uses JDBC because of native queries in script.
 *
 * @author S. Fink
 */
public class DataBasePopulator {
    private InputStream in;

    public DataBasePopulator(String scriptName) {
        in = getClass().getResourceAsStream("/" + scriptName);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                getPropertyManager().getProperty("connection.url")
        );
    }

    public void executeStatement() throws Exception {
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));
        Connection connection = getConnection();
        String line;

        line = bufRead.readLine();

        Statement statement = connection.createStatement();
        while (line != null) {
            if (line.length() > 1) {
                if (line.equals("--commit")) {
                    statement.close();
                    statement = connection.createStatement();
                } else {
                    statement.executeUpdate(line);
                }
                line = bufRead.readLine();
            } else {
                line = bufRead.readLine();
            }
        }
        statement.close();
        connection.setAutoCommit(true);
        connection.close();
        bufRead.close();
    }

    public void populate() throws Exception {
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));
        Connection connection = getConnection();
        String line;

        line = bufRead.readLine();

        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        while (line != null) {
            if (line.equals("--commit")) {
                connection.commit();
                statement.close();
                statement = connection.createStatement();
            } else {
                statement.addBatch(line);
            }
            line = bufRead.readLine();
        }
        statement.close();
        connection.setAutoCommit(true);
        connection.commit();
        connection.close();
        bufRead.close();
    }
}
