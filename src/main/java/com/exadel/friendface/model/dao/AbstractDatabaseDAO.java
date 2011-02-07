package com.exadel.friendface.model.dao;

import com.exadel.friendface.model.connection.ConnectionManager;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * User: sfink
 * Date: 2/2/11
 * Time: 12:10 PM
 */

public abstract class AbstractDatabaseDAO {
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;

    public Connection getConnection() {
        return ConnectionManager.getInstance().getConnection();
    }

    public void createPreparedStatement(String sql) throws SQLException {
        preparedStatement = getConnection().prepareStatement(sql);
    }

    public <T> void preparedSetParam(int position, T value) throws SQLException {
        preparedStatement.setObject(position, value);
    }

    public <T> void callableSetParam(int position, T value) throws SQLException {
        callableStatement.setObject(position, value);
    }

    public int executeUpdate() throws SQLException {
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public <T> T executeSelect(Class<T> beanType) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            BeanProcessor beanProcessor = new BeanProcessor();
            return beanProcessor.toBean(resultSet, beanType);
        } else {
            return null;
        }
    }

    public <T> Collection executeMultipleSelect(Class<T> beanType) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        BeanProcessor beanProcessor = new BeanProcessor();
        return beanProcessor.toBeanList(resultSet, beanType);
    }

    public void createCallable(String sql) throws SQLException {
        callableStatement = getConnection().prepareCall(sql);
    }

    public Object callableExecute(int returnParamIndex, int returnSqlType) throws SQLException {
        callableStatement.registerOutParameter(returnParamIndex, returnSqlType);
        callableStatement.execute();
        Object result = callableStatement.getObject(returnParamIndex);
        callableStatement.close();
        return result;
    }
}
