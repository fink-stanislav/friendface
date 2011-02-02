package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: sfink
 * Date: 2/2/11
 * Time: 12:10 PM
 */

public abstract class AbstractDatabaseDAO {
    private PreparedStatement preparedStatement;

    public Connection getConnection() {
        return ConnectionManager.getInstance().getConnection();
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void createPreparedStatement(String sql) throws SQLException {
        preparedStatement = getConnection().prepareStatement(sql);
    }

    public void preparedSetString(int position, String value) throws SQLException {
        preparedStatement.setString(position, value);
    }

    public void preparedSetInt(int position, int value) throws SQLException {
        preparedStatement.setInt(position, value);
    }

    public int executeUpdate() throws SQLException {
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public ResultSet executeSelect() throws SQLException {
        throw new UnsupportedOperationException();
    }
}
