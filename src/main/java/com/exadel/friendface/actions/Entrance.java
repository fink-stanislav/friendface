package com.exadel.friendface.actions;

import com.opensymphony.xwork2.Action;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/27/11
 * Time: 6:34 PM
 */

public class Entrance implements Action {
    public String execute() {
        if (true) {
            return SUCCESS;
        }
        return ERROR;
    }
}
