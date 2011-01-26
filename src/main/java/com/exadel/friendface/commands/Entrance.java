/**
 * Created by IntelliJ IDEA.
 * User: sfink
 * Date: 23.01.11
 * Time: 21:09
 */

package com.exadel.friendface.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.exadel.friendface.util.stringutil.StringUtil.buildUrl;

/**
 * Command class.
 * Checks whether user is logged in or not.
 * Uses session for getting it.
 * Returns url to forward.
 */

public class Entrance implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object loginEmail = session.getAttribute("loginEmail");
        if (loginEmail != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("message", (String) loginEmail);
            return buildUrl("/friendface/infopage", params);
        }
        return "/friendface/welcomepage";
    }

}