package com.exadel.friendface.model;

import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: sfink
 * Date: 1/31/11
 * Time: 5:05 PM
 */

public class TestConnection {
    public void insertMethod() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        String query = "INSERT INTO `user`(login, passhash) VALUES('qqq@mail.com', 123456);";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger("logger");
            logger.error("sql exc in insert method", e);
        }
    }

    public int countNotApprovedFriends() {
        Connection connection = ConnectionManager.getInstance().getConnection();

        try {
            CallableStatement proc = connection.prepareCall("{ call countNotApprovedFriends (?, ?) }");
            proc.setInt(1, 2);
            proc.registerOutParameter(2, java.sql.Types.INTEGER);
            proc.execute();
            return proc.getInt(2);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger("logger");
            logger.error("sql exc in countNotApprovedFriends method", e);
        }
        return 0;
    }
}
