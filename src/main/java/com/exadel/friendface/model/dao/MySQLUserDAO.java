package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;
import com.healthmarketscience.sqlbuilder.*;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

import java.sql.SQLException;

/**
 * Author: sfink
 * Date: 31.01.11
 * Time: 23:25
 */

public class MySQLUserDAO extends AbstractDatabaseDAO implements UserDAO {

    private DbTable userTable;
    private DbColumn id;
    private DbColumn loginEmail;
    private DbColumn passwordHash;
    private DbColumn username;
    private DbColumn userSurname;

    public MySQLUserDAO() {
        DbSpec spec = new DbSpec();
        DbSchema schema = spec.addDefaultSchema();
        userTable = schema.addTable("user");
        id = userTable.addColumn("id");
        loginEmail = userTable.addColumn("loginEmail");
        passwordHash = userTable.addColumn("passwordHash");
        username = userTable.addColumn("username");
        userSurname = userTable.addColumn("userSurname");
    }

    public int createUser(User user) {
        try {
            String insertStatement = new InsertQuery(userTable)
                    .addColumn(loginEmail, user.getLoginEmail())
                    .addColumn(passwordHash, user.getPasswordHash())
                    .addColumn(username, user.getUsername())
                    .addColumn(userSurname, user.getUserSurname())
                    .toString();
            createPreparedStatement(insertStatement);
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateUser(User user) {
        try {
            String updateStatement = new UpdateQuery(userTable)
                    .addSetClause(passwordHash, user.getPasswordHash())
                    .addSetClause(username, user.getUsername())
                    .addSetClause(userSurname, user.getUserSurname())
                    .addCondition(BinaryCondition.equalTo(loginEmail, user.getLoginEmail()))
                    .toString();
            createPreparedStatement(updateStatement);
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteUser(User user) {
        try {
            String deleteStatement = new DeleteQuery(userTable)
                    .addCondition(BinaryCondition.equalTo(loginEmail, user.getLoginEmail()))
                    .toString();
            createPreparedStatement(deleteStatement);
            return executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public User getUser(Integer userId) {
        try {
            String selectStatement = new SelectQuery()
                    .addAllTableColumns(userTable)
                    .addCondition(BinaryCondition.equalTo(id, userId))
                    .toString();
            createPreparedStatement(selectStatement);
            return executeSelect(User.class);
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUser(String loginEmail) {
        try {
            String selectStatement = new SelectQuery()
                    .addAllTableColumns(userTable)
                    .addCondition(BinaryCondition.equalTo(this.loginEmail, loginEmail))
                    .toString();
            createPreparedStatement(selectStatement);
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
