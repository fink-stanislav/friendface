package com.exadel.friendface.service.user;

import com.exadel.friendface.controllers.actions.FriendfaceAction;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.view.beans.LoginBean;
import com.exadel.friendface.view.beans.RegistrationBean;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * Author: S. Fink
 * Date: 1/28/11
 * Time: 2:52 PM
 */

public class UserUtils {

    public static User getUserFromBean(RegistrationBean registrationBean) {
        User user = new User();
        user.setLoginEmail(registrationBean.getLoginEmail());
        user.setUsername(registrationBean.getUsername());
        user.setUserSurname(registrationBean.getUserSurname());
        user.setPasswordHash(getPasswordHash(registrationBean.getPassword()));
        return user;
    }

    public static String getPasswordHash(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes("UTF8"));
            byte[] resultByte = messageDigest.digest();
            return new String(Hex.encodeHex(resultByte));
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkCredentials(User userFromRequest, User userFromDb) {
        return userFromDb.getLoginEmail().equals(userFromRequest.getLoginEmail()) &&
               userFromDb.getPasswordHash().equals(userFromRequest.getPasswordHash());
    }

    public static String getUserSessionKey() {
        return FriendfaceAction.FRIENDFACE_USER;
    }

    public static User getUserFromBean(LoginBean loginBean) {
        User user = new User();
        user.setLoginEmail(loginBean.getLoginEmail());
        user.setPasswordHash(getPasswordHash(loginBean.getPassword()));
        return user;
    }
}
