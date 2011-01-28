package com.exadel.friendface.actions;

import com.exadel.friendface.beans.User;
import com.exadel.friendface.pagebeans.LogonBean;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import static com.exadel.friendface.business.Authentication.isUserExists;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends ActionSupport implements ModelDriven {
    private LogonBean logonBean = new LogonBean();

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(logonBean.getLoginEmail());
            validator.validatePassword(logonBean.getPassword());
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    public String execute() throws Exception {
        User user = new User();
        user.setLoginEmail(logonBean.getLoginEmail());
        user.setPasswordHash(logonBean.getPassword().hashCode());

        if (isUserExists(user)) {
            // perform login
            return SUCCESS;
        }
        return ERROR;
    }

    public Object getModel() {
        return logonBean;
    }
}
