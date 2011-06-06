package net.friendface.friendface.controllers.actions.user;

import net.friendface.friendface.controllers.actions.UserAction;
import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.service.FriendfaceService;

import javax.jcr.Binary;
import java.io.InputStream;

/**
 * Author: S. Fink
 * Date: 06.06.11
 * Time: 0:45
 */

public class ShowUserpic extends UserAction {
    private InputStream inputStream;

    @Override
    public String execute() {
        try {
            User user = FriendfaceService.getService().getUserService().getById(userId);
            Binary content = user.getContent();
            if (content == null) {
                inputStream = getClass().getResourceAsStream("/images/no_image.png");
            } else {
                inputStream = content.getStream();
            }
            return SUCCESS;
        } catch (Exception e) {
            return resultAndErrorMessage(ERROR, e.getMessage());
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
