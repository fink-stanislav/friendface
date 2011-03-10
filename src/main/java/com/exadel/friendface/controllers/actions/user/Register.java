package com.exadel.friendface.controllers.actions.user;

import com.exadel.friendface.controllers.actions.StandardAction;
import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import com.exadel.friendface.view.beans.RegistrationBean;
import com.opensymphony.xwork2.ModelDriven;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * Author: S. Fink
 * Date: 1/27/11
 * Time: 6:17 PM
 */

public class Register extends StandardAction implements ModelDriven {
    private RegistrationBean registrationBean = new RegistrationBean();

    @Override
    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(registrationBean.getLoginEmail());
            validator.validateName(registrationBean.getUsername());
            validator.validateName(registrationBean.getUserSurname());
            validator.validatePassword(registrationBean.getPassword());
            validator.validatePassword(registrationBean.getPasswordConfirmation());
            validator.validatePassword(registrationBean.getPassword(), registrationBean.getPasswordConfirmation());
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    @Override
    public String execute() {
        try {
            return register();
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    private String register() throws Exception {
        if (getService().getUserService().register(registrationBean)) {
            addActionMessage(getText("registration.succeed"));
            return SUCCESS;
        } else {
            return resultAndErrorMessage(INPUT, getText("user.already.exists"));
        }
    }

    public Object getModel() {
        return registrationBean;
    }
}
