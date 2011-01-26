package com.exadel.friendface.commands;

import com.exadel.friendface.util.stringutil.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
            try {
                return StringUtil.buildUrl("/friendface/infopage", params);
            } catch (UnsupportedEncodingException e) {
                return "/friendface/welcomepage";
            }
        }
        return "/friendface/logon";
    }

}
