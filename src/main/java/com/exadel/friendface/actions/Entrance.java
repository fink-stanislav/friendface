package com.exadel.friendface.actions;

import com.exadel.friendface.business.Authentication;
import com.opensymphony.xwork2.Action;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/27/11
 * Time: 6:34 PM
 */

public class Entrance implements Action {
    public String execute() {
        if (Authentication.isUserAlreadyLogged()) {
            return ERROR;
        }
        return SUCCESS;
    }
}
