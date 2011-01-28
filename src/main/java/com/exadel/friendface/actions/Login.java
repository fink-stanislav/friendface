package com.exadel.friendface.actions;

import com.exadel.friendface.beans.User;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;

import static com.exadel.friendface.business.Authentication.isUserExists;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends ActionSupport {
    private String password;
    private String loginEmail;

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

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(getLoginEmail());
            validator.validatePassword(getPassword());
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    public String execute() throws Exception {
        User user = new User();
        user.setLoginEmail(getLoginEmail());
        user.setPasswordHash(getPassword().hashCode());

        if (isUserExists(user)) {
            // perform login
            return SUCCESS;
        }
        return ERROR;
    }
}
