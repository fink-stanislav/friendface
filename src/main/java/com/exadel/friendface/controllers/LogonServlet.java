package com.exadel.friendface.controllers;

import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.exadel.friendface.util.servletutil.ServletUtil.redirectToUrl;

public class LogonServlet extends HttpServlet {

    private Logger logger;

    @Override
    public void init() {
        logger = Logger.getLogger("logger");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        Validator validator = new Validator();
        String loginEmail = request.getParameter("loginEmail");
        String password = request.getParameter("password");

        try {
            validator.validateEmail(loginEmail);
            validator.validatePassword(password);

            // login here
            HttpSession session = request.getSession();
            if (session.getAttribute("loginEmail") == null) {
                session.setAttribute("loginEmail", loginEmail);
            }
            response.sendRedirect("/friendface/LogonServlet");

        } catch (ValidationException ve) {
            logger.error(ve);
            Map<String, String> params = new HashMap<String, String>();
            params.put("loginEmail", loginEmail);
            params.put("errormessage", ve.toString());
            redirectToUrl(this, request, response, "/friendface/logon", params);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object atribute = session.getAttribute("loginEmail");
        if (atribute != null) {
            Map<String, String> atributes = new HashMap<String, String>();
            atributes.put("message", (String) atribute);
            redirectToUrl(this, request, response, "/friendface/infopage", atributes);
        }
    }
}
