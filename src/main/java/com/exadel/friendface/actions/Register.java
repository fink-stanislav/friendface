package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.RegistrationBean;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import static com.exadel.friendface.model.util.UserUtils.getUserFromBean;

/**
 * Author: S. Fink
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
        try {
            return register();
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    private String register() throws Exception {
        User user = getUserFromBean(registrationBean);
        UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();

        if (!userDAO.isUserExists(user)) {
            userDAO.createUser(user);
            addActionMessage(getText("registration.succeed"));
            return SUCCESS;
        } else {
            return resultAndErrorMessage(INPUT, getText("user.already.exists"));
        }
    }

    private String resultAndErrorMessage(String result, String errorMessage) {
        addActionError(errorMessage);
        return result;
    }

    public Object getModel() {
        return registrationBean;
    }
}
