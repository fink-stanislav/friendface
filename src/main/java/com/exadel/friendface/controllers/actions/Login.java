package com.exadel.friendface.controllers.actions;

import com.exadel.friendface.controllers.validation.ValidationException;
import com.exadel.friendface.controllers.validation.Validator;
import com.exadel.friendface.view.beans.LoginBean;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;

import javax.persistence.NoResultException;
import java.util.Map;

import static com.exadel.friendface.service.FriendfaceService.getService;

/**
 * User: S. Fink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends StrutsAction implements ModelDriven, SessionAware {
    private Map session;
    private LoginBean loginBean = new LoginBean();

    @Override
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
        if (getService().getUserService().loginUser(loginBean, session)) {
            return SUCCESS;
        } else {
            return resultAndErrorMessage(INPUT, getText("wrong.password"));
        }
    }

    public void validate() {
        Validator validator = new Validator();
        try {
            validator.validateEmail(loginBean.getLoginEmail());
            validator.validatePassword(loginBean.getPassword());
        } catch (ValidationException e) {
            addActionError(getText(e.toString()));
        }
    }

    public Object getModel() {
        return loginBean;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}