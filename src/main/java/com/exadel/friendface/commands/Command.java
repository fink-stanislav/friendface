package com.exadel.friendface.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: ффф
 * Date: 23.01.11
 * Time: 20:41
 */

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
