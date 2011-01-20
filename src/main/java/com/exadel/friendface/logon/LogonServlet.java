package com.exadel.friendface.logon;

import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.exadel.friendface.servletutil.ServletUtil.forwardErrorPage;

public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogonServlet() {
        super();
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
    		response.sendRedirect("/friendface/underconstruction");
    	} catch (ValidationException ve) {
    		forwardErrorPage(this, request, response, ve.toString());
    	}
	}

}
