package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.connection.ConnectionManager;
import com.exadel.friendface.model.exceptions.ModelUserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: sfink
 * Date: 31.01.11
 * Time: 23:25
 */

public class UserDAO {
    public int insertUser(User user) throws SQLException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        String insertStatement = "INSERT INTO `user` (loginEmail, passwordHash, username, userSurname) VALUES(?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
        preparedStatement.setString(1, user.getLoginEmail());
        preparedStatement.setInt(2, user.getPasswordHash());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getUserSurname());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public int updateUser(User user) throws SQLException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        String insertStatement = "UPDATE `user` SET passwordHash = ?, username = ?, userSurname = ? WHERE `user`.loginEmail = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
        preparedStatement.setInt(1, user.getPasswordHash());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getUserSurname());
        preparedStatement.setString(4, user.getLoginEmail());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public int deleteUser(User user) throws SQLException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        String insertStatement = "DELETE FROM `user` WHERE `user`.loginEmail = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
        preparedStatement.setString(1, user.getLoginEmail());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public User findUserByLogin(User user) throws SQLException, ModelUserException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        String selectStatement = "SELECT `user`.loginEmail, `user`.username, `user`.userSurname, `user`.passwordHash FROM `user` WHERE `user`.loginEmail = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
        preparedStatement.setString(1, user.getLoginEmail());
        ResultSet resultSet = preparedStatement.executeQuery();

        User result = new User();
        if (resultSet.next()) {
            result.setLoginEmail(resultSet.getString("loginEmail"));
            result.setUsername(resultSet.getString("username"));
            result.setUserSurname(resultSet.getString("userSurname"));
            result.setPasswordHash(resultSet.getInt("passwordHash"));
        } else {
            throw new ModelUserException();
        }

        preparedStatement.close();
        return result;
    }
}
