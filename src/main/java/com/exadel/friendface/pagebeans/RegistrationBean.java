package com.exadel.friendface.pagebeans;

import static com.exadel.friendface.stringutil.StringUtil.avoidNullValue;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/21/11
 * Time: 11:32 AM
 */

public class RegistrationBean {
    private String loginEmail;
    private String username;
    private String usersurname;
    private String errormessage;

    public String getLoginEmail() {
        return avoidNullValue(loginEmail);
    }

    public String getUsername() {
        return avoidNullValue(username);
    }

    public String getUsersurname() {
        return avoidNullValue(usersurname);
    }

    public String getErrormessage() {
        return avoidNullValue(errormessage);
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsersurname(String usersurname) {
        this.usersurname = usersurname;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
}
