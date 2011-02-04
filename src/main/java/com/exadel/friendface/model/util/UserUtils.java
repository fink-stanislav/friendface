package com.exadel.friendface.model.util;

import com.exadel.friendface.beans.pagebeans.LogonBean;
import com.exadel.friendface.beans.pagebeans.RegistrationBean;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.system.FriendfaceConstants;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * Author: sfink
 * Date: 1/28/11
 * Time: 2:52 PM
 */

public class UserUtils {

    public static User getUser(RegistrationBean registrationBean) {
        User user = new User();
        user.setLoginEmail(registrationBean.getLoginEmail());
        user.setUsername(registrationBean.getUsername());
        user.setUserSurname(registrationBean.getUserSurname());
        user.setPasswordHash(getPasswordHash(registrationBean.getPassword()));
        return user;
    }

    public static User getUser(LogonBean logonBean) {
        User user = new User();
        user.setLoginEmail(logonBean.getLoginEmail());
        user.setPasswordHash(getPasswordHash(logonBean.getPassword()));
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

    public static String getUserSessionKey() {
        return FriendfaceConstants.FriendfaceUser.name();
    }
}
