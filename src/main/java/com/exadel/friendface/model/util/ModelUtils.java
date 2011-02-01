package com.exadel.friendface.model.util;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.beans.pagebeans.RegistrationBean;
import com.exadel.friendface.model.connection.ConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: sfink
 * Date: 1/28/11
 * Time: 2:52 PM
 */

public class ModelUtils {
    public static boolean isUserExists(User user) throws SQLException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        CallableStatement procedure = connection.prepareCall("{ call isUserExists (?, ?) }");
        procedure.setString("loginEmail", user.getLoginEmail());
        procedure.registerOutParameter("result", java.sql.Types.BOOLEAN);
        procedure.execute();
        Boolean result = procedure.getBoolean("result");
        procedure.close();
        return result;
    }

    public static User getUser(RegistrationBean registrationBean) {
        User user = new User();
        user.setLoginEmail(registrationBean.getLoginEmail());
        user.setPasswordHash(registrationBean.getPassword().hashCode());
        user.setUsername(registrationBean.getUsername());
        user.setUserSurname(registrationBean.getUserSurname());
        return user;
    }

    public static boolean isPasswordRight(User user) throws SQLException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        CallableStatement procedure = connection.prepareCall("{ call isPasswordRight (?, ?, ?) }");
        procedure.setString("loginEmail", user.getLoginEmail());
        procedure.setInt("passwordHash", user.getPasswordHash());
        procedure.registerOutParameter("result", java.sql.Types.BOOLEAN);
        procedure.execute();
        Boolean result = procedure.getBoolean("result");
        procedure.close();
        return result;
    }
}
