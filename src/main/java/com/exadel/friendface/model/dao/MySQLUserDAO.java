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

public class MySQLUserDAO extends AbstractDatabaseDAO implements UserDAO {

    public int createUser(User user) {
        try {
            createPreparedStatement("INSERT INTO `user` (loginEmail, passwordHash, username, userSurname) VALUES(?, ?, ?, ?);");
            preparedSetString(1, user.getLoginEmail());
            preparedSetInt(2, user.getPasswordHash());
            preparedSetString(3, user.getUsername());
            preparedSetString(4, user.getUserSurname());
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateUser(User user) {
        try {
            createPreparedStatement("UPDATE `user` SET passwordHash = ?, username = ?, userSurname = ? WHERE `user`.loginEmail = ?;");
            preparedSetInt(1, user.getPasswordHash());
            preparedSetString(2, user.getUsername());
            preparedSetString(3, user.getUserSurname());
            preparedSetString(4, user.getLoginEmail());
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteUser(User user) {
        try {
            createPreparedStatement("DELETE FROM `user` WHERE `user`.loginEmail = ?;");
            preparedSetString(1, user.getLoginEmail());
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public User getUser(int userId) {
        return null;
    }

    public User getUser(String loginEmail) {
        try {
            createPreparedStatement("SELECT `user`.loginEmail, `user`.username, `user`.userSurname, `user`.passwordHash FROM `user` WHERE `user`.loginEmail = ?;");
            preparedSetString(1, loginEmail);
            ResultSet resultSet = getPreparedStatement().executeQuery();

            User result = new User();
            if (resultSet.next()) {
                result.setLoginEmail(resultSet.getString("loginEmail"));
                result.setUsername(resultSet.getString("username"));
                result.setUserSurname(resultSet.getString("userSurname"));
                result.setPasswordHash(resultSet.getInt("passwordHash"));
            }
            getPreparedStatement().close();
            return result;
        } catch (SQLException e) {
            return null;
        }
    }
}
