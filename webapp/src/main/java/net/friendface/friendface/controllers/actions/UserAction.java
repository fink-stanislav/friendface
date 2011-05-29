package net.friendface.friendface.controllers.actions;

/**
 * Author: S. Fink
 * Date: 29.05.11
 * Time: 20:36
 */

public abstract class UserAction extends StandardAction {
    protected Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
