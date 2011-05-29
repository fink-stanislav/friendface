package net.friendface.friendface.view.beans;

import net.friendface.friendface.model.entities.User;
import net.friendface.friendface.model.enums.ContactState;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:00
 */

public class UserBean extends User {
    private ContactState state;

    public UserBean(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setUserSurname(user.getUserSurname());
        setLoginEmail(user.getLoginEmail());
    }

    public String getState() {
        return state.name();
    }

    public void setState(ContactState state) {
        this.state = state;
    }
}
