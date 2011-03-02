package com.exadel.friendface.actions;

import com.exadel.friendface.beans.pagebeans.LogonBean;
import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import javax.persistence.NoResultException;
import java.util.Map;

import static com.exadel.friendface.model.dao.DAOFactory.getDAOFactory;
import static com.exadel.friendface.model.util.UserUtils.*;

/**
 * User: S. Fink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends ActionSupport implements ModelDriven, SessionAware {
    private Map session;
    private LogonBean logonBean = new LogonBean();

    public String execute() {
        try {
            return login();
        } catch (NoResultException e) {
            return resultAndErrorMessage(INPUT, getText("no.user"));
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    private String login() throws Exception {
        User userFromRequest = getUserFromBean(logonBean);
        User userFromStorage = getUserFromStorage(userFromRequest);

        if (checkCredentials(userFromRequest, userFromStorage)) {
            session.put(getUserSessionKey(), userFromStorage);
            return SUCCESS;
        } else {
            return resultAndErrorMessage(INPUT, getText("wrong.password"));
        }
    }

    private String resultAndErrorMessage(String result, String errorMessage) {
        addActionError(errorMessage);
        return result;
    }

    private User getUserFromStorage(User user) throws Exception {
        return getDAOFactory().getUserDAO().getUser(user.getLoginEmail());
    }

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(logonBean.getLoginEmail());
            validator.validatePassword(logonBean.getPassword());
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    public Object getModel() {
        return logonBean;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}
