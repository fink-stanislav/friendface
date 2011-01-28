/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/21/11
 * Time: 11:32 AM
 */

package com.exadel.friendface.pagebeans;

import static com.exadel.friendface.util.stringutil.StringUtil.avoidNullValue;

public class RegistrationBean {
    private String loginEmail;
    private String username;
    private String userSurname;
    private String password;
    private String passwordConfirmation;

    public String getLoginEmail() {
        return avoidNullValue(loginEmail);
    }

    public String getUsername() {
        return avoidNullValue(username);
    }

    public String getUserSurname() {
        return avoidNullValue(userSurname);
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public void setUsername(String username) {
        this.username = avoidNullValue(username);
    }

    public void setUsersurname(String usersurname) {
        this.userSurname = usersurname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
