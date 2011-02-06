package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.LogonBean;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;
import static com.exadel.friendface.model.util.UserUtils.*;

/**
 * User: sfink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends ActionSupport implements ModelDriven, SessionAware {
    private Map session;
    private LogonBean logonBean = new LogonBean();

    public String execute() {
        try {
            return login();
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, "Internal application error. " + e.getMessage());
        }
    }

    private String login() throws Exception {
        User userFromRequest = getUserFromBean(logonBean);
        User userFromStorage = getUserFromStorage(userFromRequest);

        if (userFromStorage == null) {
            return resultAndErrorMessage(INPUT, "No such user.");
        }
        if (isUserLoggedIn(userFromRequest)) {
            return resultAndErrorMessage(ERROR, "User is already logged in.");
        }
        if (checkCredentials(userFromRequest, userFromStorage)) {
            session.put(getUserSessionKey(), userFromStorage);
            getDAOFactory().getAuthorizationDAO().loginUser(userFromStorage);
            return SUCCESS;
        } else {
            return resultAndErrorMessage(INPUT, "Wrong password.");
        }
    }

    private String resultAndErrorMessage(String result, String errorMessage) {
        addActionError(errorMessage);
        return result;
    }

    private User getUserFromStorage(User user) throws Exception {
        return getDAOFactory().getUserDAO().getUser(user.getLoginEmail());
    }

    private boolean isUserLoggedIn(User user) throws Exception {
        return getDAOFactory().getAuthorizationDAO().isUserLoggedIn(user);
    }

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(logonBean.getLoginEmail());
            validator.validatePassword(logonBean.getPassword());
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    public Object getModel() {
        return logonBean;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
