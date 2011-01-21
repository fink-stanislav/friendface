package com.exadel.friendface.pagebeans;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/21/11
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class LogonBean {
    private String loginEmail;
    private String errormessage;

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
}
