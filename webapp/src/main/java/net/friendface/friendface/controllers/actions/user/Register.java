package net.friendface.friendface.controllers.actions.user;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.view.beans.RegistrationBean;

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
            if (FriendfaceService.getService().getUserService().register(registrationBean)) {
                addActionMessage(getText("registration.succeed"));
                return SUCCESS;
            } else {
                return resultAndErrorMessage(INPUT, getText("user.already.exists"));
            }
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    public Object getModel() {
        return registrationBean;
    }
}
