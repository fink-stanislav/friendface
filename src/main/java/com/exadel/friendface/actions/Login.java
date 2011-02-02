package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.LogonBean;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.util.UserUtils;
import com.exadel.friendface.system.FriendfaceConstants;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * User: sfink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends ActionSupport implements ModelDriven, SessionAware {
    private Map session;
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

    public String execute() {
        try {
            User user = UserUtils.getUser(logonBean);
            if (DAOFactory.getDAOFactory().getUserDAO().checkCredentials(user)) {
                session.put(FriendfaceConstants.FriendfaceUser, user);
                return SUCCESS;
            } else {
                return ERROR;
            }
        } catch (Exception e) {
            addActionError("Internal application error. " + e.getMessage());
            return ERROR;
        }
    }

    public Object getModel() {
        return logonBean;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
