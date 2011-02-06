package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.User;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.DeleteQuery;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.UpdateQuery;
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
        userTable = schema.addTable("users");
        id = userTable.addColumn("id");
        loginEmail = userTable.addColumn("loginEmail");
        passwordHash = userTable.addColumn("passwordHash");
        username = userTable.addColumn("username");
        userSurname = userTable.addColumn("userSurname");
    }

    public int createUser(User user) throws SQLException {
        String insertStatement = new InsertQuery(userTable)
                .addColumn(loginEmail, user.getLoginEmail())
                .addColumn(passwordHash, user.getPasswordHash())
                .addColumn(username, user.getUsername())
                .addColumn(userSurname, user.getUserSurname())
                .toString();
        createPreparedStatement(insertStatement);
        return executeUpdate();
    }

    public int updateUser(User user) throws SQLException {
        String updateStatement = new UpdateQuery(userTable)
                .addSetClause(passwordHash, user.getPasswordHash())
                .addSetClause(username, user.getUsername())
                .addSetClause(userSurname, user.getUserSurname())
                .addCondition(BinaryCondition.equalTo(loginEmail, user.getLoginEmail()))
                .toString();
        createPreparedStatement(updateStatement);
        return executeUpdate();
    }

    public int deleteUser(User user) throws SQLException {
        String deleteStatement = new DeleteQuery(userTable)
                .addCondition(BinaryCondition.equalTo(loginEmail, user.getLoginEmail()))
                .toString();
        createPreparedStatement(deleteStatement);
        return executeUpdate();
    }

    public User getUser(Integer userId) throws SQLException {
        String selectStatement = new SelectQuery()
                .addAllTableColumns(userTable)
                .addCondition(BinaryCondition.equalTo(id, userId))
                .toString();
        createPreparedStatement(selectStatement);
        return executeSelect(User.class);
    }

    public User getUser(String loginEmail) throws SQLException {
        String selectStatement = new SelectQuery()
                .addAllTableColumns(userTable)
                .addCondition(BinaryCondition.equalTo(this.loginEmail, loginEmail))
                .toString();
        createPreparedStatement(selectStatement);
        return executeSelect(User.class);
    }

    public Boolean isUserExists(User user) throws SQLException {
        createCallable("{ call isUserExists (?, ?) }");
        callableSetParam(1, user.getLoginEmail());
        return (Boolean) callableExecute(2, java.sql.Types.BOOLEAN);
    }
}
