/**
 * User: S. Fink
 * Date: 1/21/11
 * Time: 11:32 AM
 */

package net.friendface.friendface.view.beans;

import java.io.Serializable;

public class RegistrationBean implements Serializable {
    private String loginEmail;
    private String username;
    private String userSurname;
    private String password;
    private String passwordConfirmation;

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
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
