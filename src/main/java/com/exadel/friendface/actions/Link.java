package com.exadel.friendface.actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/28/11
 * Time: 12:50 PM
 */

public class Link extends ActionSupport {
    public String welcome() {
        return "friendface.welcomepage";
    }

    public String registration() {
        return "friendface.registration";
    }

    public String logon() {
        return "friendface.logon";
    }

    public String infopage() {
        return "friendface.infopage";
    }
}
