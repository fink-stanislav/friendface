package com.exadel.friendface.controllers;

import com.exadel.friendface.pagebeans.RegistrationBean;
import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.exadel.friendface.util.servletutil.ServletUtil.redirectToUrl;

public class RegistrationServlet extends HttpServlet {
    private Logger logger;

    @Override
    public void init() {
        logger = Logger.getLogger("logger");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Validator validator = new Validator();

        String loginEmail = request.getParameter("loginEmail");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String username = request.getParameter("username");
        String usersurname = request.getParameter("usersurname");

        try {
            validator.validateEmail(loginEmail);
            validator.validateTextString(username);
            validator.validateTextString(usersurname);
            validator.validatePassword(password);
            validator.validatePassword(passwordConfirmation);
            validator.validatePassword(password, passwordConfirmation);

            // registration here
            response.sendRedirect("/friendface/RegistrationServlet");

        } catch (ValidationException ve) {
            logger.error(ve);
            Map<String, String> params = new HashMap<String, String>();
            params.put("loginEmail", loginEmail);
            params.put("username", username);
            params.put("usersurname", usersurname);
            params.put("errormessage", ve.toString());
            redirectToUrl(this, request, response, "registration", params);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("message", "Registration succeeded.");
        redirectToUrl(this, request, response, "/friendface/infopage", params);
    }
}
