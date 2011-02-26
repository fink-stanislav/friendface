package com.exadel.friendface.beans.pagebeans;

import java.io.Serializable;

/**
 * User: S. Fink
 * Date: 1/21/11
 * Time: 1:56 PM
 */

public class LogonBean implements Serializable {
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