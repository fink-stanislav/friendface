package net.friendface.friendface.service.user;

import net.friendface.friendface.controllers.actions.FriendfaceAction;
import net.friendface.friendface.model.dao.friends.FriendDAO;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.friends.FriendUtils;
import net.friendface.friendface.view.beans.LoginBean;
import net.friendface.friendface.view.beans.RegistrationBean;
import net.friendface.friendface.view.beans.UserBean;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

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

    public static List<UserBean> usersToUserBeans(User currentUser, List<User> users, FriendDAO friendDAO) {
        List<UserBean> result = new ArrayList<UserBean>(users.size());
        for (User user : users) {
            UserBean bean = new UserBean(user);
            bean.setState(FriendUtils.getContactState(currentUser, user, friendDAO));
            result.add(bean);
        }
        return result;
    }
}
