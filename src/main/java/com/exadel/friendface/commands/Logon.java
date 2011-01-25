package com.exadel.friendface.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/25/11
 * Time: 12:59 PM
 */
public class Logon implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/friendface/logon";
    }
}
