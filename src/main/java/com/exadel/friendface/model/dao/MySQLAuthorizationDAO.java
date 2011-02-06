package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.entities.Authorization;
import com.exadel.friendface.model.entities.User;
import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.DeleteQuery;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbColumn;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;

import java.sql.SQLException;

/**
 * User: sfink
 * Date: 2/4/11
 * Time: 4:32 PM
 */

public class MySQLAuthorizationDAO extends AbstractDatabaseDAO implements AuthorizationDAO {
    private DbTable authTable;
    private DbColumn userId;
    private DbColumn authData;

    public MySQLAuthorizationDAO() {
        super();
        DbSpec spec = new DbSpec();
        DbSchema schema = spec.addDefaultSchema();
        authTable = schema.addTable("authorization");
        userId = authTable.addColumn("userId");
        authData = authTable.addColumn("authData");
    }

    public int loginUser(User user) throws SQLException {
        String insertStatement = new InsertQuery(authTable)
                .addColumn(userId, user.getId())
                .addColumn(authData, user.getLoginEmail())
                .toString();
        createPreparedStatement(insertStatement);
        return executeUpdate();
    }

    public int logoutUser(User user) throws SQLException {
        String deleteStatement = new DeleteQuery(authTable)
                .addCondition(BinaryCondition.equalTo(userId, user.getId()))
                .toString();
        createPreparedStatement(deleteStatement);
        return executeUpdate();
    }

    public Boolean isUserLoggedIn(User user) throws SQLException {
        String selectStatement = new SelectQuery()
                .addAllTableColumns(authTable)
                .addCondition(BinaryCondition.equalTo(userId, user.getId()))
                .toString();
        createPreparedStatement(selectStatement);
        return null != executeSelect(Authorization.class);
    }
}
