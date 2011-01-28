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
 * Time: 6:17 PM
 */

public class Register extends ActionSupport {
    private String loginEmail;
    private String username;
    private String userSurname;
    private String password;
    private String passwordConfirmation;

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

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(getLoginEmail());
            validator.validateTextString(getUsername());
            validator.validateTextString(getUserSurname());
            validator.validatePassword(getPassword());
            validator.validatePassword(getPasswordConfirmation());
            validator.validatePassword(getPassword(), getPasswordConfirmation());
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    public String execute() {
        User user = new User();
        user.setLoginEmail(getLoginEmail());
        user.setPasswordHash(getPassword().hashCode());

        if (!isUserExists(user)) {
            // perform registration
            return SUCCESS;
        }
        return ERROR;
    }
}
