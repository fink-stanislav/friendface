package com.exadel.friendface.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.exadel.friendface.util.stringutil.StringUtil.*;

/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 1/25/11
 * Time: 12:59 PM
 */

public class Logon implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object loginEmail = session.getAttribute("loginEmail");
        if (loginEmail != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("message", (String) loginEmail);
            return buildUrl("/friendface/infopage", params);
        }
        return "/friendface/logon";
    }

}
