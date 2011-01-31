package com.exadel.friendface.actions;

import com.exadel.friendface.beans.business.User;
import com.exadel.friendface.beans.pagebeans.RegistrationBean;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import static com.exadel.friendface.business.Authentication.isUserExists;

/**
 * Author: sfink
 * Date: 1/27/11
 * Time: 6:17 PM
 */

public class Register extends ActionSupport implements ModelDriven {
    private RegistrationBean registrationBean = new RegistrationBean();

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(registrationBean.getLoginEmail());
            validator.validateTextString(registrationBean.getUsername());
            validator.validateTextString(registrationBean.getUserSurname());
            validator.validatePassword(registrationBean.getPassword());
            validator.validatePassword(registrationBean.getPasswordConfirmation());
            validator.validatePassword(registrationBean.getPassword(), registrationBean.getPasswordConfirmation());
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    public String execute() {
        User user = new User();
        user.setLoginEmail(registrationBean.getLoginEmail());
        user.setPasswordHash(registrationBean.getPassword().hashCode());

        if (!isUserExists(user)) {
            // perform registration
            return SUCCESS;
        }
        return ERROR;
    }

    public Object getModel() {
        return registrationBean;
    }
}
