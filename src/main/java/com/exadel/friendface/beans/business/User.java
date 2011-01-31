package com.exadel.friendface.beans.business;

/**
 * User: sfink
 * Date: 1/28/11
 * Time: 2:50 PM
 */

public class User {
    private String loginEmail;
    private int passwordHash;

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }
}
