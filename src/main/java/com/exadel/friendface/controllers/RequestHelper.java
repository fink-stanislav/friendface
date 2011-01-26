package com.exadel.friendface.controllers;

import com.exadel.friendface.commands.Command;
import com.exadel.friendface.commands.CommandCreationException;
import com.exadel.friendface.commands.CommandFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 23.01.11
 * Time: 20:39
 */

public class RequestHelper {
    private HttpServletRequest request;

    public RequestHelper(HttpServletRequest request) {
        this.request = request;
    }

    public Command getCommand() throws CommandCreationException {
        return CommandFactory.getInstance().create(request.getParameter("activity"));
    }
}
