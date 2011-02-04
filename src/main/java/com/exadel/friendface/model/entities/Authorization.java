package com.exadel.friendface.model.entities;

/**
 * User: sfink
 * Date: 2/4/11
 * Time: 5:36 PM
 */

public class Authorization {
    private Integer userId;
    private String authData;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthData() {
        return authData;
    }

    public void setAuthData(String authData) {
        this.authData = authData;
    }
}
