package com.exadel.friendface.logon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exadel.friendface.validation.ValidationException;
import com.exadel.friendface.validation.Validator;

import static com.exadel.friendface.servletutil.ServletUtil.forvardErrorPage;
import static com.exadel.friendface.servletutil.ServletUtil.forvardPage;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
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
			response.sendRedirect("pages/errorpages/underconstruction.html");
		}
		catch (ValidationException ve) {
			forvardPage(this, request, response, ve.toString(), "/pages/registration/registration.jsp");
		}
	}
}
