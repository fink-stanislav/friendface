package net.friendface.friendface.controllers.actions.settings;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.actions.helpers.SessionHelper;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserService;
import net.friendface.friendface.service.user.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Author: S. Fink
 * Date: 06.06.11
 * Time: 0:58
 */

public class RenameUser extends UserAction implements SessionAware {
    private SessionHelper sessionHelper;
    private String newName;
    private String newSurname;

    @Override
    public void validate() {
        try {
            Validator validator = new Validator();
            validator.validateName(newName);
            validator.validateName(newSurname);
        } catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    @Override
    public String execute() {
        try {
            UserService service = FriendfaceService.getService().getUserService();
            User user = (User) sessionHelper.getFromSession(UserUtils.getUserSessionKey());
            if (StringUtils.isNotBlank(newName)) {
                user.setUsername(newName);
            }
            if (StringUtils.isNotBlank(newSurname)) {
                user.setUserSurname(newSurname);
            }
            service.renameUser(user);
            userId = user.getId();
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionHelper = new SessionHelper(session);
    }
}
