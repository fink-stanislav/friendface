package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.LogonBean;
import com.exadel.friendface.model.dao.DAOFactory;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.util.UserUtils;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.model.util.UserUtils.getUserSessionKey;

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

    private boolean isUserLoggedIn(User user) throws Exception {
        return DAOFactory.getDAOFactory().getAuthorizationDAO().isUserLoggedIn(user);
    }

    private Boolean checkCredentials(User user) throws Exception {
        return DAOFactory.getDAOFactory().getAuthorizationDAO().checkCredentials(user);
    }

    private User getUser(LogonBean logonBean) throws Exception {
        return DAOFactory.getDAOFactory().getUserDAO().getUser(UserUtils.getUser(logonBean).getLoginEmail());
    }

    private String login() throws Exception {
        User user = getUser(logonBean);
        if (user == null) {
            addActionError("Login or password is wrong.");
            return INPUT;
        } else {
            if (isUserLoggedIn(user)) {
                addActionError("User is already logged in.");
                return ERROR;
            }
            if (checkCredentials(user)) {
                session.put(getUserSessionKey(), user);
                DAOFactory.getDAOFactory().getAuthorizationDAO().loginUser(user);
                return SUCCESS;
            } else {
                addActionError("Login or password is wrong.");
                return INPUT;
            }
        }
    }

    public String execute() {
        try {
            return login();
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
