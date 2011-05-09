package net.friendface.friendface.controllers.actions.user;

import com.opensymphony.xwork2.ModelDriven;
import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.view.beans.LoginBean;
import org.apache.struts2.interceptor.SessionAware;

import javax.persistence.NoResultException;
import java.util.Map;

import static net.friendface.friendface.service.user.UserUtils.getUserSessionKey;

/**
 * User: S. Fink
 * Date: 1/27/11
 * Time: 6:28 PM
 */

public class Login extends StandardAction implements ModelDriven, SessionAware {
    private LoginBean loginBean = new LoginBean();
    private SessionHelper sessionHelper;

    @Override
    public String execute() {
        try {
            User userFromStorage = FriendfaceService.getService().getUserService().login(loginBean);
            if (userFromStorage != null) {
                sessionHelper.putToSession(getUserSessionKey(), userFromStorage);
                return SUCCESS;
            } else {
                return resultAndErrorMessage(INPUT, getText("wrong.password") + " or " + getText("no.user"));
            }
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, getText("internal.app.error") + e.getMessage());
        }
    }

    @Override
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

    public void setSession(Map<String, Object> stringObjectMap) {
        sessionHelper = new SessionHelper(stringObjectMap);
    }
}
