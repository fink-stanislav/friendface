package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.RegistrationBean;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.util.UserUtils;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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

    private User getUser(RegistrationBean registrationBean) throws Exception {
        return DAOFactory.getDAOFactory().getUserDAO().getUser(UserUtils.getUser(registrationBean).getLoginEmail());
    }

    private String register() throws Exception {
        User user = getUser(registrationBean);
        UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
        if (!userDAO.isUserExists(user)) {
            userDAO.createUser(user);
            addActionMessage("Registration succeed. ");
            return SUCCESS;
        } else {
            addActionError("Such user is already exists. ");
            return INPUT;
        }
    }

    public String execute() {
        try {
            return register();
        } catch (Exception e) {
            addActionError("Internal application error. " + e.getMessage());
            return ERROR;
        }
    }

    public Object getModel() {
        return registrationBean;
    }
}
