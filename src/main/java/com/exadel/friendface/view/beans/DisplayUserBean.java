package com.exadel.friendface.view.beans;

import com.exadel.friendface.model.entities.User;
import com.exadel.friendface.model.enums.ContactState;

/**
 * Author: S. Fink
 * Date: 15.03.11
 * Time: 23:00
 */

public class DisplayUserBean {
    private User user;
    private ContactState state;

    public DisplayUserBean(User user, ContactState state) {
        this.user = user;
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContactState getState() {
        return state;
    }

    public void setState(ContactState state) {
        this.state = state;
    }
}
