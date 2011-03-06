package com.exadel.friendface.view.beans;

import java.io.Serializable;

/**
 * Author: S. Fink
 * Date: 3/2/11
 * Time: 11:34 PM
 */

public class SearchPeopleBean implements Serializable {
    private String loginEmail;
    private String username;
    private String userSurname;

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
