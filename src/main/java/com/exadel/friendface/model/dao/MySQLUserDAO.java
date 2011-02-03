package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;

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
            preparedSetParam(1, user.getLoginEmail());
            preparedSetParam(2, user.getPasswordHash());
            preparedSetParam(3, user.getUsername());
            preparedSetParam(4, user.getUserSurname());
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateUser(User user) {
        try {
            createPreparedStatement("UPDATE `user` SET passwordHash = ?, username = ?, userSurname = ? WHERE `user`.loginEmail = ?;");
            preparedSetParam(1, user.getPasswordHash());
            preparedSetParam(2, user.getUsername());
            preparedSetParam(3, user.getUserSurname());
            preparedSetParam(4, user.getLoginEmail());
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteUser(User user) {
        try {
            createPreparedStatement("DELETE FROM `user` WHERE `user`.loginEmail = ?;");
            preparedSetParam(1, user.getLoginEmail());
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public User getUser(Integer userId) {
        try {
            createPreparedStatement("SELECT `user`.id `user`.loginEmail, `user`.username, `user`.userSurname, `user`.passwordHash FROM `user` WHERE `user`.id = ?;");
            preparedSetParam(1, userId);
            return executeSelect(User.class);
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUser(String loginEmail) {
        try {
            createPreparedStatement("SELECT `user`.id `user`.loginEmail, `user`.username, `user`.userSurname, `user`.passwordHash FROM `user` WHERE `user`.loginEmail = ?;");
            preparedSetParam(1, loginEmail);
            return executeSelect(User.class);
        } catch (SQLException e) {
            return null;
        }
    }

    public Boolean isUserExists(User user) {
        try {
            createCallable("{ call isUserExists (?, ?) }");
            callableSetParam(1, user.getLoginEmail());
            return (Boolean) callableExecute(2, java.sql.Types.BOOLEAN);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean checkCredentials(User user) {
        User checkedUser = getUser(user.getLoginEmail());
        return checkedUser != null && checkedUser.getPasswordHash().equals(user.getPasswordHash());
    }
}
