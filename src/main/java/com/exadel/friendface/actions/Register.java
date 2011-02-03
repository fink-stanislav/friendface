package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.RegistrationBean;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.dao.UserDAO;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import static com.exadel.friendface.model.dao.DAOFactory.StorageEngineType.*;
import static com.exadel.friendface.model.util.UserUtils.getUser;

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
        try {
            User user = getUser(registrationBean);
            UserDAO userDAO = DAOFactory.getDAOFactory(mysql).getUserDAO();
            if (!userDAO.isUserExists(user)) {
                userDAO.createUser(user);
                addActionMessage("Registration succeed. ");
                return SUCCESS;
            } else {
                addActionError("Such user already exists. ");
                return INPUT;
            }
        } catch (Exception e) {
            addActionError("Internal application error. " + e.getMessage());
            return ERROR;
        }
    }

    public Object getModel() {
        return registrationBean;
    }
}
