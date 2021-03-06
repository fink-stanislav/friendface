package net.friendface.friendface.view.beans;

import java.io.Serializable;

/**
 * User: S. Fink
 * Date: 1/21/11
 * Time: 1:56 PM
 */

public class LoginBean implements Serializable {
    private String loginEmail;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }
}