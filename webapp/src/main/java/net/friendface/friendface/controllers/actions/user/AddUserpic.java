package net.friendface.friendface.controllers.actions.user;

import net.friendface.friendface.controllers.actions.StandardAction;
import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.controllers.validation.ValidationException;
import net.friendface.friendface.controllers.validation.Validator;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;
import net.friendface.friendface.service.user.UserService;

import javax.jcr.RepositoryException;
import java.io.File;

/**
 * Author: S. Fink
 * Date: 06.06.11
 * Time: 0:59
 */

public class AddUserpic extends UserAction {
    private File pictureFile;

    @Override
    public void validate() {
        try{
            Validator validator = new Validator();
            validator.notNull(pictureFile);
        }
        catch (ValidationException e) {
            addActionError(e.toString());
        }
    }

    @Override
    public String execute() {
        try {
            UserService service = FriendfaceService.getService().getUserService();
            User user = service.getById(userId);
            service.addUserPic(user, pictureFile);
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
        return SUCCESS;
    }

    public void setPictureFile(File pictureFile) {
        this.pictureFile = pictureFile;
    }
}
